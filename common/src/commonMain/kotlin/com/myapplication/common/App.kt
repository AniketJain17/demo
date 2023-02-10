import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapplication.common.database.ReminderEntityDao
import com.myapplication.common.database.getDatabase


private fun loginAuthentication(username:String,password:String){
    if(username =="Admin" && password=="Admin12"){
        println("working")
    }else{
        println("not working")
    }
}

@Composable
fun AppScreen(userInfo: UserInfo) {
    if (!userInfo.userLoggedIn) {
        LoginScreen(userInfo)
    } else {
        UserInfoScreen(userInfo)
    }
}

@Composable
fun UserInfoScreen(userInfo: UserInfo) {
    Column(modifier = Spacing(8.dp)) {
        Text(
            text = "Username: ${userInfo.userName}",
            modifier = Spacing(8.dp),
            style = (+MaterialTheme.typography()).h6
        )

    }
}

@Composable
fun App(contextProvider1: ContextProvider){
    val reminderDao = remember { ReminderEntityDao(db = getDatabase(contextProvider)) }
    val reminders = reminderDao.selectAll().collectAsState(emptyList())
    AppContents(
        reminders = reminders,
        onSubmit = reminderDao::insert
    )
}

@Composable
fun loginScreen(){

}

@Composable
fun AppContents(
    reminders: State<List<Reminder>>,
    onSubmit: (Reminder) -> Unit
) {
    val scrollState = rememberScrollState()
    MaterialTheme {
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            var userPassword by remember { mutableStateOf("") }
            var userName by remember { mutableStateOf("") }
            Column(modifier = Modifier.padding(50.dp)) {
                Text("Login Page", textAlign = TextAlign.Center, color = Color.Blue, fontSize = 50.sp)
            }
            //Todo : Input Username And Password From To login To next page

            Column(modifier = Modifier.padding(10.dp)) {
                Text("Username", textAlign = TextAlign.Center)

                OutlinedTextField(
                    value = userName,
                    onValueChange = { userName = it },
                    label = { Text("Username") }
                )
            }
            Column(modifier = Modifier.padding(10.dp)) {
                Text("Password", textAlign = TextAlign.Center)
                OutlinedTextField(
                    value = userPassword,
                    onValueChange = { userPassword = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
            Button(
                modifier = Modifier.padding(horizontal = 20.dp),
                onClick = {
                    loginAuthentication(userName,userPassword)
                    val reminder = Reminder(
                        name = userName,
                        password = userPassword,
                    )
                    onSubmit(reminder)
                    // Reset the fields
                    userName = ""
                    userPassword = ""
                }
            ) {
                Text("Submit")
            }
            // All Saved Reminders
            RemindersList(reminders)
        }
    }
}

expect fun getPlatformName(): String