package huette.gruin

import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.sql.*
import java.sql.DriverManager

import org.owasp.encoder.Encoders
import java.io.PrintWriter
import java.nio.CharBuffer
import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.close
import org.owasp.encoder.Encode
import org.owasp.encoder.Encoder
import java.lang.reflect.Array.getFloat
import java.sql.ResultSet
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class Database(val filename: String) {



    init {
        var c: Connection? = null
        var stmt: Statement? = null

        try {
            Class.forName("org.sqlite.JDBC")
            c = DriverManager.getConnection("jdbc:sqlite:$filename")
            println("Opened database successfully")

            stmt = c!!.createStatement()
            val sql: String = "CREATE TABLE IF NOT EXISTS Messages(hostname VARCHAR(30), message TEXT, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP);"
            println(sql)
            stmt!!.executeUpdate(sql)
            stmt.close()
            c.close()
        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            System.exit(0)
        }

        println("Table created successfully")
    }


    fun insertIntoDatabase(message: String, sender: String) {
        var c: Connection? = null
        var stmt: Statement? = null

        try {
            Class.forName("org.sqlite.JDBC")
            c = DriverManager.getConnection("jdbc:sqlite:$filename")
            c!!.autoCommit = false

            stmt = c.createStatement()
            val host = XSSUnescape(sender)
            val msg = XSSUnescape(message)
            val sql = "INSERT INTO Messages(hostname, message) VALUES('$host', '$msg');"
            stmt!!.executeUpdate(sql)

            stmt.close()
            c.commit()
            c.close()
        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            System.exit(0)
        }
    }


    fun XSSUnescape(str: String): String {
        return Encode.forJavaScript(str)
    }

    fun retreiveFromDatabase(): List<JsonObject> {
        var c: Connection? = null
        var stmt: Statement? = null
        val list = mutableListOf<JsonObject>()

        try {
            Class.forName("org.sqlite.JDBC")
            c = DriverManager.getConnection("jdbc:sqlite:$filename")
            c!!.autoCommit = false
            println("Opened database successfully")

            stmt = c.createStatement()
            val rs = stmt!!.executeQuery("SELECT * FROM Messages ORDER BY timestamp DESC;")

            while (rs.next()) {
                val hostname = rs.getString("hostname")
                val message = rs.getString("message")
                val ts = rs.getString("timestamp")


                list.add(jsonObject(
                        "hostname" to hostname,
                        "message" to message,
                        "timestamp" to ts
                )
                )
            }
            rs.close()
            stmt.close()
            c.close()
        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            System.exit(0)
        }

        return list
    }


    fun simpleTest() {


        val db = Database("meine.db")

        println("Created")

        db.insertIntoDatabase("123.456.789.0", "Meine Nachricht")

        println("Inserted")

        db.retreiveFromDatabase().forEach { println(it.toString()) }

        println(Gson().toJson(db.retreiveFromDatabase()))
    }
}
