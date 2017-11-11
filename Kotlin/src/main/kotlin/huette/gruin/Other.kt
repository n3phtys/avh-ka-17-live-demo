package huette.gruin

import com.google.gson.Gson
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import org.jetbrains.ktor.routing.*
import org.jetbrains.ktor.application.*
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.application.*
import org.jetbrains.ktor.features.*
import org.jetbrains.ktor.host.*
import org.jetbrains.ktor.http.*
import org.jetbrains.ktor.netty.*
import org.jetbrains.ktor.response.*
import org.jetbrains.ktor.routing.*
import org.jetbrains.ktor.request.*     // for recieve
import org.jetbrains.ktor.util.*

fun buildServer() {
    val db = Database("meine.db")


    launch(CommonPool) {

        embeddedServer(Netty, 8080) {
            routing {
                get("/") {
                    call.respondText(htmlForMessenger, ContentType.Text.Html)
                }
                get("/index.html") {
                    call.respondText(htmlForMessenger, ContentType.Text.Html)
                }
                post(endpoint) {
                    val post = call.receive<ValuesMap>()
                    val message = post["message"]
                    val sender = call.request.local.remoteHost

                    if (message != null) {
                        db.insertIntoDatabase(message, sender)
                    }
                    call.respondText("Post worked!", ContentType.Text.Plain)
                }
                get(endpoint) {
                    call.respondText(Gson().toJson(db.retreiveFromDatabase()), ContentType.Text.Any)
                }

            }
        }.start(wait = true)


    }
    Thread.sleep(30000)
}