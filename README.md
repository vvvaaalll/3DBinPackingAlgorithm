# 3D bin packing problem
Ideja rješenja
---
Rješenje slaganja kutija zamišljeno je tako da se kutije u prostoriju slažu po slojevima. Prva kutija u sloju uvijek je ona s najvećom površinom donje plohe, bez rotiranja.
visina sloja jednaka je visini prve kutije koja je stavljena na taj sloj, te je svim ostalim kutijama na tom sloju visina te kutije gornje ograničenje. Nakon polaganja prve kutije
u sloju, preostali volumen dijeli se na 2 volumena te se oba volumena posebno popunjavaju. U slučaju da kutija s najvećom površinom donje plohe ne stane u prostoriju, uzima se prva koja stane.
Nakon polaganja prve kutije, ostatak razine popunjava se po principu first fit, te se rekurzivnim pozivom preostali volumen dijeli na dva volumena sve dok je preostale volumene moguće popunjavati.


<img src="https://user-images.githubusercontent.com/59445209/153604838-18565a26-3544-4af6-a445-7f2570a25149.jpg" width=100% height=100%>  <br />

Klasa Box
---
<br />
Klasa Box sadrži atribute dimenzija kutitije, tip kutije i volumen. Dana klasa ima svoje Get metode i metodu za rotiranje kutije koja rotira na načina da 
izmijenjuje dimenzije na lsijedeći način: vrijednost dimenije na prvom mjestu ide na treće mjesto, druge na prvo, a treće na drugo čime se obilaskom kroz for petlju 3 puta 
kutija rotira u 3 moguće pozicije.<br />
Osim rotiranja kutije, klasa box ima statičku metodu za generiranje N kutija danog tipa. metoda kao atribute prima tip kutije, broj kutija toga tipa te dimenzije željenog tipa kutija. 
Metoda kao povratnu vrijednost vraća listu kutija. <br />

Klasa Level
---
<br />
Klasa Level brine o kutijama spremljenim na danu visinu. packedBoxes atribut je ove klase. Tip ovog atributa je ArrayList<Box> te se u njega spremaju kutije koje su spremljene na toj razini.<br />
Dana klasa ima 2 metode: GetPackedBoxes vraća listu kutija spremljenih na toj razini, dok metoda Add za parametar prima objekt klase Box te tu kutiju dodaje u packedBoxes listu.

Klasa Container
---
<br />
Klasa Container predstavlja prostoriju u koju se slažu kutije. Klasa ima atribude dimensions tipa HashMap, u koju se spremaju dimenzije prostorije te atribud volume koji se izračunava u konstruktoru.<br />
Uz ove atribute, klasa ima 3 metode: metodu za izmjenu dimenzija prostorije i metode za dohvaćanje dimenzija i volumena prostorije.

Klasa Packing
---
<br />
Klasa Packing ima tri atributa: boxes, container i levels te 8 metoda: Get metode, TryToFit, FitBox, CountOfBoxesFit, HeightOfPackedStack, PackLevel i Fill space.<br /><br />
Metoda TryToFit uspoređuje dimenzije prostora i kutije te vraća boolean vrijednost stane li kutija u prostor ili ne <br /><br />
Metoda FitBox kao parametre prima dostupan prostor i kutiju koju se želi spremiti u dani prostor. Metoda poziva metodu TryToFit, ukoliko kutija ne stane u dani prostor, nad njom se unutar for petlje poziva metoda RotateBox. ukoliko nakon rotiranja kutije kutija stane u prostor, prekida se daljnje rotiranje kutije.<br /><br />
Metoda CountOfBoxesFit vraća broj kutija spremljenih u sve levele. <br /><br />
Metoda HeightOfPackedStack vraća visinu spremljenih razina na koje su spremljene kutije. Koristi se za kontrolu visine naslaganih kutija kako se ne bi prelazila visina prostorije.<br /><br />
Metoda PackLevel popunjava razinu na način da uzima kutiju s najvećom površinom donje plohe te tu kutiju stavlja u razinu prvu. visina razine postaje visina kutije koja je prva stavljena na danu razinu.
Ukoliko kutija s najvećom površinom ne stane prva, uzima se prva ljedeća kutija koja stane. Nakon smještene prve kutije, preostali volumen se dijeli na 2 volumena te se za svaki poziva metoda FillSpace koja ih popunjava.<br /><br />
Metoda FillSpace kao parametre priam dostupan prostor i razinu na koju se kutije spremaju. Metoda u dostupan prostor sprema kutije po principu first fit, nakon čega se rekurzivno poziva kako bi se popunila preostala dva volumena, sve dok se razina ne popuni.


