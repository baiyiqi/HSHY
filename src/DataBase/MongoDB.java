package DataBase;

import Log.Logs;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
//import com.mongodb.DBCursor;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoCursor;
//import com.mongodb.client.MongoDatabase;

/**
 * Created by yiqibai on 12/27/15.
 */
public abstract class MongoDB implements DataBase {
    private String ip;
    private int port;
    private String db;
    private String url;
    MongoClient mongo;
    Logs loger;



    MongoDB(String ip, int port, Logs loger) {
        this.loger = loger;
        this.ip = ip;
        this.port = port;
        this.url = String.format("mongodb://%s:%s@%s:27017/?authSource=admin&authMechanism=SCRAM-SHA-1",
                "root", "wtroot", this.ip);
        Connect();
    }



    @Override
    public void Connect() {
        try {
            MongoClientURI murl = new MongoClientURI(url);
            mongo = new MongoClient(murl);
        } catch (Exception e) {
            loger.server("MongoException [" + e.getMessage() + "]");
        }
    }



    @Override
    public void Close() {
        mongo.close();

    }



    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getIp() {
        return ip;
    }


    public void setPort(int port) {
        this.port = port;
    }


    public int getPort() {
        return port;
    }


    public void setDb(String db) {
        this.db = db;
    }


    public String getDb() {
        return db;
    }

}
