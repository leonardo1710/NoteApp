package at.ac.fhcampuswien.mvvmdemo.widgets

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import at.ac.fhcampuswien.mvvmdemo.models.Note
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun NoteCard(note: Note){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp) {

        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = note.date,
                    style = MaterialTheme.typography.caption
                )

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "remove note")
                }
            }

            Text(
                text = note.text,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun NoteCards(notes: List<Note> = listOf()){
    LazyColumn {
        items(notes) { note ->
            NoteCard(note)
        }
    }
}

@Composable
fun AddNoteWidget(){
    Text(text = "Add a Note",
        style = MaterialTheme.typography.h5,
        color = MaterialTheme.colors.primaryVariant)

    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { value -> text = value},
        label = { Text(text = "Note")}
    )

    Button(
        modifier = Modifier.padding(16.dp),
        onClick = {
            val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm")
            val currentDate = sdf.format(Date())
            val newNote = Note(text, currentDate)

            Log.d("AddNoteScreen", "added ${newNote.text} ${newNote.date}")
        }) {

        Text( text = "Save")
    }
}

