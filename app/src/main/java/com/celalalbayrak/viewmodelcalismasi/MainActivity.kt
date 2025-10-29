package com.celalalbayrak.viewmodelcalismasi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

// ----------- VIEWMODEL -----------
class SayacViewModel : ViewModel() {    // SayacViewModel sınıfı, ViewModel sınıfından türetildi
    var sayi by mutableStateOf(0) // sayi değişkeni, mutableStateOf ile başlatıldı
        private set                     // sayi değişkeni sadece SayacViewModel sınıfından erişilebilir

    fun arttir() { sayi++ }     // sayi değişkeni 1 artırılıyor
    fun azalt() { sayi-- }      // sayi değişkeni 1 azaltılıyor
    fun sifirla() { sayi = 0 } // sayi değişkeni sıfırlanıyor
    fun ikiyecarp() { sayi = sayi * 2 } // sayi değişkeni 2 ile çarpılıyor
}

class RenkViewModel : ViewModel() {         // RenkViewModel sınıfı, ViewModel sınıfından türetildi
    var renk by mutableStateOf(Color.Red)   // renk değişkeni , mutableStateOf ile başlatıldı kırmızı olacak.

    fun maviYap() { renk = Color.Blue }     // renk değişkeni mavi olacak
    fun kirmiziYap() { renk = Color.Red }   // renk değişkeni kırmızı olacak
}

// ----------- UI (VIEW) -----------
@Composable
fun SayacEkrani(viewModel: SayacViewModel = viewModel(), // SayacEkrani composable fonksiyonu, SayacViewModel sınıfından bir viewModel nesnesi alıyor
                modifier: Modifier = Modifier) // ve bir Modifier nesnesi alıyor
{
    Column(
        modifier = modifier
            .background(Color.Yellow)
            .padding(6.dp),
        verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sayı: ${viewModel.sayi}",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(2.dp))

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 100.dp),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item { Button(onClick = { viewModel.azalt() }) { Text("Azalt") } }
            item { Button(onClick = { viewModel.sifirla() }) { Text("Sıfırla") } }
            item { Button(onClick = { viewModel.arttir() }) { Text("Artır") } }
            item { Button(onClick = { viewModel.ikiyecarp() }) { Text("2 Çarp") } }
            item {
                Card(
                    modifier = Modifier.size(70.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text("Kart içi Yeni Metin", modifier = Modifier.padding(8.dp))
                }
            }
            item {
                var checked by remember { mutableStateOf(false) }
                Switch(
                    checked = checked,
                    onCheckedChange = { checked = it }
                )
            }
        }
    }
}

@Composable
fun RenkEkrani(renkViewModel: RenkViewModel = viewModel(), modifier: Modifier = Modifier) {
    Column(
        modifier = modifier                         // boyutlandırma
            .fillMaxWidth()                         // genişlik kapla
            .background(renkViewModel.renk)                 // renk durumuna göre renk değişikliği
            .padding(16.dp), // boşluk ver
        verticalArrangement = Arrangement.Center,           // dikeyde ortala (vertical)
        horizontalAlignment = Alignment.CenterHorizontally // yatayda ortala (horizontal)
    ) {
        Button(
            onClick = { renkViewModel.maviYap() },          // tıklandığında rengi değiştiren fonksiyon
            colors = ButtonDefaults.buttonColors(           // buton rengi
                containerColor = if (renkViewModel.renk == Color.Blue) Color.Yellow else Color.Gray
            )
        ) {
            Text("Mavi yap")                             // buton metni
        }

        Spacer(modifier = Modifier.height(8.dp))            // boşluk bırak

        Button(onClick = { renkViewModel.kirmiziYap() }) { // tıklandığında rengi değiştiren fonksiyon
            Text(
                "Kırmızı yap",
                color = Color.Black,                // text rengi siyah olsun
                fontFamily = FontFamily.Serif       // yazı tipi
            )
        }
    }
}

// ----------- MAINACTIVITY -----------
class MainActivity : ComponentActivity() {                       // MainActivity sınıfı, ComponentActivity sınıfından türetildi
    private val viewModel: SayacViewModel by viewModels()       // viewModel nesnesi oluşturuldu ve sadece mainaktivity içinde çalış

    override fun onCreate(savedInstanceState: Bundle?) {        // onCreate fonksiyonu override edildi
        super.onCreate(savedInstanceState)                      // super sınıfın onCreate fonksiyonu çağırıldı
        setContent {                                            // setContent fonksiyonu ile içeriği ayarlandı
            MaterialTheme {                                     // tema uygulandı
                Column(modifier = Modifier.fillMaxSize()) {     // column ile alt alta sıralandı
                    SayacEkrani(viewModel, modifier = Modifier.weight(1f)) // SayacEkrani composable fonksiyonu çağırıldı ve weight ile boyutlandırıldı
                    RenkEkrani(modifier = Modifier.weight(1f))  // RenkEkrani composable fonksiyonu çağırıldı ve weight ile boyutlandırıldı
                }
            }
        }
    }
}

// ----------- ÖNİZLEME (Preview) -----------
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TumEkranPreview() {
    val fakeSayac = SayacViewModel()
    val fakeRenk = RenkViewModel()

    MaterialTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            SayacEkrani(fakeSayac, modifier = Modifier.weight(1f))
            RenkEkrani(fakeRenk, modifier = Modifier.weight(1f))
        }
    }
}
