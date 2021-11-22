package server;

import com.sun.net.httpserver.HttpServer;

import constants.Exceptions;
import fetchers.DataBase;
import fetchers.DataBaseFetcher;

import fetchers.PostgresDataBase;
import logging.Logger;
import logging.LoggerFactory;

import routes.Route;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;

class Server {

    public static final DataBase dataBase = new PostgresDataBase("jdbc:postgresql://db:5432/postgres", "postgres", Env.POSTGRES_PASSWORD);

    public static void main(String[] args) {
        Logger l = LoggerFactory.getLogger();
        for (int retries = 0; retries < 5; retries++) {
            try {
                dataBase.connectAndMigrate();
                break;
            } catch (Exceptions.DataBaseException e) {
                l.warn("could not connect to database, retrying");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException te) {
                    // this doesn't need to be handled because it'll only be interrupted
                    // if the program is stopped while it's sleeping,
                    // in which case the program is already exiting, and we don't
                    // need to report any errors.
                }
            }
        }
        try {
            dataBase.connectAndMigrate();
        } catch (Exceptions.DataBaseException e) {
            l.error("could not connect to database: ", e);
            System.exit(-1);
            return;
        }
        try {
            dataBase.insertPlaceholderData();
        } catch (Exceptions.DataBaseException e) {
            l.error("placeholder data file not found", e);
            System.exit(-1);
            return;
        }
        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(Env.PORT), 0);
        } catch (IOException e) {
            l.error("could not start server", e);
            System.exit(-1);
            return;
        }
        for (Route route : Routes.routes) {
            server.createContext(route.getContext(), route);
        }
        server.setExecutor(null);
        server.start();
    }
}
