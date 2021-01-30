import java.net.URLDecoder.decode
import java.sql.*
import java.util.*
import kotlin.Any.*;

private val TABLE_LOGIN = "login"
private val TABLE_BLOG = "blog"
private val DB_PATH = "shortened.db"

class DB {

    private lateinit var connection: Connection

    init {
        try {
            // create a connection to the database
            connection = DriverManager.getConnection("jdbc:sqlite:$DB_PATH")
            println("Connection to SQLite has been established.")

            createTable(connection)
            println("Table created")
        } catch (e: SQLException) {
            println(e.message)
            e.printStackTrace()
        }
    }


    private fun createTable(connection: Connection) {
        val stmt = connection.createStatement()
        stmt.execute("""
CREATE TABLE IF NOT EXISTS login (
                                            login TEXT NOT NULL,
                                            id_login int NOT NULL,
                                            key int NOT NULL,
                                            
                                            PRIMARY KEY(login, id_login, key)
)
    """.trimIndent())
        stmt.execute("""
        CREATE TABLE IF NOT EXISTS $TABLE_BLOG (
            id int NOT NULL,
            message TEXT NOT NULL

        )
    """.trimIndent())
    }


    fun getIdFromLogin(login: String) : Int? {
        val stmt: Statement = connection.createStatement()
        val resultSet: ResultSet = stmt.executeQuery("SELECT * FROM $TABLE_LOGIN WHERE login=\"$login\"")
        while(resultSet.next()){
            val idLogin = resultSet.getInt("id_login");
            return idLogin;
        }
        return null
    }

    fun getIdFromKey(key: Int) : Int? {
        val stmt: Statement = connection.createStatement()
        val resultSet: ResultSet = stmt.executeQuery("SELECT * FROM $TABLE_LOGIN WHERE key=\"$key\"")
        while(resultSet.next()){
            val idLogin = resultSet.getInt("id_login");
            return idLogin;
        }
        return null
    }

    fun getLoginFromId(id: Int) : String? {
        val stmt: Statement = connection.createStatement()
        val resultSet: ResultSet = stmt.executeQuery("SELECT * FROM $TABLE_LOGIN WHERE id_login=\"$id\"")
        while(resultSet.next()){
            val login = resultSet.getString("login");
            return decode(login);
        }
        return null
    }

    fun setTriple(login : String, id : Int, key : Int) : Int {
        val stmt: Statement = connection.createStatement()
        val rs: ResultSet = stmt.executeQuery("SELECT * FROM $TABLE_LOGIN WHERE login=\"$login\"")
        while (rs.next()) {
            return 1;
        }
        stmt.execute("INSERT INTO $TABLE_LOGIN (login, id_login, key) VALUES (\"$login\",\"$id\", \"$key\")")
        return 0;
    }

    fun getMessagesFromId(id: Int) : MutableList<String> {
        val stmt: Statement = connection.createStatement()
        val resultSet: ResultSet = stmt.executeQuery("SELECT * FROM $TABLE_BLOG WHERE id=\"$id\"")
        val result = mutableListOf("");
        while(resultSet.next()){
            val login = resultSet.getString("message");
            result += decode(login)
        }
        return result;
    }

    fun addMessage(id : Int, message : String) : Int {
        val stmt: Statement = connection.createStatement()
        stmt.execute("INSERT INTO $TABLE_BLOG (id, message) VALUES (\"$id\", \"$message\")")
        return 0;
    }

}
