# SOE-SZTA-2021-Sasok
MapAndMove branch ami a mozgás és térkép implementálására van. 
* Jelenlegi állapot: 
  * tudunk mozogni a térképen nyilakkal
  * mozgás irányát és egyéb utasításokat, mint "Falnak ütköztél" vagy "Szörnyel találkoztál" a rendszer text formájában kiírja
  * ha találkozunk egy szörnnyel, akkor meg tudunk kűzdeni vele, szintje random 1-3
  * a program JTextArea-ra írja a harc eredményét
  * automatikus build test pushok-nál
 

* Jelenlegi hibák:
  * ha meghalt hősünk, továbbra is tud mozogni és harcolni
  * ugyanazzal a szörnnyel meg tudunk többször is vívni
  * nincs vége a játéknak és nincs újrakezdésre lehetőség
  * nehéz kiigazodni hol vagyunk éppen a pályán

![image](https://user-images.githubusercontent.com/60651308/121823689-32ded080-cca7-11eb-9329-99ef5dc0d859.png)

![image](https://user-images.githubusercontent.com/60651308/121823710-573aad00-cca7-11eb-8616-b0f2d027f01a.png)

![image](https://user-images.githubusercontent.com/60651308/121823743-894c0f00-cca7-11eb-814a-efcb46fb8565.png)

