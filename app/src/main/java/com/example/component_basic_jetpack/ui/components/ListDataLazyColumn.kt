package com.example.component_basic_jetpack.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.component_basic_jetpack.model.data.Person

@Composable
fun PersonListItem(person: Person){
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = person.name)
            Text(text = "Usia ${person.age}")
        }
    }

}

@Composable
fun PersonList(persons: List<Person>){
    LazyColumn {
        items(persons){ person ->
            PersonListItem(person = person)

        }
    }
}

@Preview
@Composable
fun PersonListItemPreview(){
    val persons = listOf(
        Person("John Doe", 25),
        Person("jane", 37),
        Person("Bob", 44)
    )
    PersonList(persons = persons)
}