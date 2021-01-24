import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*
import java.io.File
import java.util.*

fun Application.main() {
    routing {

        val db = DB()
        val random = Random()

        get("/") {
            call.respondHtml {
                head {
                    meta {
                        charset = "utf-8"
                    }
                    title {
                        +"Twegram"
                    }
                    link {
                        href = "/css/external.css"
                        rel = "stylesheet"
                    }
                    script {
                        src = "/client.js"
                    }
                }
                body {
                    header {
                        h1 {
                            +"Twegram"
                        }
                    }
                    section {
                        classes = setOf("main")
                        div {
                            classes = setOf("form")
                            h1 {
                                +"Registration"
                            }
                            input {
                                id = "nickname"
                                placeholder = "Type your nick"
                            }
                            button {
                                id = "sign"
                                p {
                                    +"Sign up"
                                }
                            }
                        }
                        div {
                            classes = setOf("form")
                            h1 {
                                +"Twe"
                            }
                            input {
                                id = "key"
                                placeholder = "Type secret key"

                            }
                            textArea {
                                id = "message"
                                placeholder = "Type your message"
                                classes = setOf("special")
                            }
                            button {
                                id = "post"
                                p {
                                    +"Post"
                                }
                            }
                        }
                    }
                    footer {
                        classes = setOf("footer");
                        h2 {
                            +"Had made by Somebody"
                        }
                    }

                }
            }
        }

        get("/blog") {
            val id : Int = call.parameters["id"]!!.toInt();
            println("id of requesting blog is $id")
            val messages = db.getMessagesFromId(id);
            val login = db.getLoginFromId(id);
            call.respondHtml {
                head {
                    meta {
                        charset = "utf-8"
                    }
                    title{
                        +"Twegram"
                    }
                    link {
                        href = "/css/external.css"
                        rel = "stylesheet"
                    }
                }
                body {
                    header {
                        h1 {
                            +"Twegram"
                        }
                    }
                    section {
                        classes = setOf("main")
                        div {
                            classes = setOf("name");
                            +"Blog by $login"
                        }
                        for (message in messages){
                            if (message != "") {
                                div {
                                    classes = setOf("blog_item")
                                    h3 {
                                        +message
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        post("/registration") {
            val login : String = call.receiveText();
            println("registration $login")
            val id = random.nextInt();
            val key = random.nextInt();
            if (db.setTriple(login, id, key) == 0){
                    call.respondText { "$key" }
            } else {
                call.respondText { "$login is used" };
            }
        }

        post("/post") {
            val body = call.receiveText().split(":").toTypedArray();
            val key = body[0].toInt();
            val message = body[1];
            val id : Int? = db.getIdFromKey(key);
            if (id != null){
                db.addMessage(id, message);
                call.respondRedirect("http://0.0.0.0:8080/blog?id=$id")
            }
            call.respond(HttpStatusCode.BadRequest, "invalid key")
        }

        static("/") {
            resources("/")
        }

    }
}