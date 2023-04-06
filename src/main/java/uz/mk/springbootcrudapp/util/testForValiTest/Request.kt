//package uz.fidobiznes.digitalbank.util.testForValiTest
//
//import fido.valitest.RequestData
//
//class Request : RequestData {
//    private val uid = rand()
//    override val headers: Map<String, String> = mapOf(
//        "Authorization" to "Bearer sadgssgsgadsg", // Добавляем хэдер авторизации
//        "Content-Type" to "application/json", // Хэдер с Content-Type, т.к. без него сервер может неправльно нас понять
//        "requestId" to uid
//    )
//    override val method: String = "POST"
//    override val params: Map<String, String> = emptyMap()
//    override val url: String = "http://10.50.50.116:8093/1.0.0/binding"
//
//
//    private fun rand(): String{
//        return java.util.UUID.randomUUID().toString()
//    }
//}