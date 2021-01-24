import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.*
import kotlinx.html.dom.create
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.xhr.XMLHttpRequest

fun main() {
    window.onload = {

        val signButton = document.getElementById("sign")!!
        val postButton = document.getElementById("post")!!
        postButton.addEventListener("click", {
            console.log("kek");
            val input = (document.getElementById("key") as HTMLInputElement)
            val input_text = (document.getElementById("message") as HTMLTextAreaElement)
            console.log("kek");
            val http = XMLHttpRequest()
            http.open("POST", "/post", true);
            http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            http.send(encodeURIComponent(input.value) + ":" +encodeURIComponent(input_text.value))
        })
        signButton.addEventListener("click", {
            val input = (document.getElementById("nickname") as HTMLInputElement)
            val http = XMLHttpRequest()
            http.open("POST", "/registration", true);
            http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            http.onload = {
                if (http.status in 200..399) {
                    input.apply {
                        value = http.responseText
                        setAttribute("readonly", "true")
                    }
                }
            }

            http.send(encodeURIComponent(input.value))
        })
    }
}

external fun encodeURIComponent(s: String): String