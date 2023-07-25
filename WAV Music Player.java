package soundproject3package3;

import mygenericdoublylinkedlistpackage.*;
import soundsystempackage.*;

import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



/**
 * @author RADHIKA
 * Sub-LinkedList
 * Contains Artist and songs
 *
 */

class SongPath {
Scanner sc;
String path;
String songName;
SimpleAudioPlayer s1;

SongPath(String path, String songName){
sc = new Scanner(System.in);
this.path = path;
this.songName = songName;
}

/**
* @throws UnsupportedAudioFileException
* @throws IOException
* @throws LineUnavailableException
* Plays a single song Clip
*
*/
void playSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
s1 = new SimpleAudioPlayer(path);

while (true)
{
System.out.println("Song Options:\nStop the song to go to the previous or next one");
System.out.println("1. PAUSE");
System.out.println("2. RESUME");
System.out.println("3. RESTART");
System.out.println("4. STOP");

int c = sc.nextInt();
s1.gotoChoice(c);
if (c == 4) {
break;
}
}

s1.resetAudioStream();
return;

}

public String toString() {
return String.format("%s", songName);

}
}
class Album {
MyDoublyLinkedList<SongPath> q;
String artist;


Album(String name, SongPath s1) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
q = new MyDoublyLinkedList<SongPath>();
artist = name;
q.insertEnd(s1);

}

/**
* @param s Song Path
* adds song to Album
*/
void addSong(SongPath s) {
q.insertEnd(s);
}

/**
* Displays all songs in the Album
*/
void displayAlbum() {

System.out.println(artist);
q.printList();
System.out.println();
}



public String toString() {
return "Artist: "+artist+" "+q.getSize()+" Songs\n";
}

}
/**
 * @author RADHIKA
 * Main-LinkedList
 * Contains Albums
 * 1. Arijit Singh
 * 2. Jubin Nautiyal
 * 3. Shreya Ghoshal
 * 4. Shankar Mahadevan
 */
class Playlist{
static String filepath = "C:\\Users\\PALLAVI\\Downloads\\file_example_WAV_1MG.wav";
Scanner scsc = new Scanner(System.in);;
MyDoublyLinkedList<Album> p1;
Album a1;
Album a2;
Album a3;
Album a4;
boolean flag;  //playlist album status prev/next
boolean exitFlag;

Playlist() throws UnsupportedAudioFileException, IOException, LineUnavailableException{

p1 = new MyDoublyLinkedList<Album>(); //Main Doubly LL for artists

//Creating albums (not inserted in Playlist q yet)
a1 = new Album("Arijit Singh", new SongPath("C:\\Users\\PALLAVI\\Downloads\\Illahi - Yeh Jawaani Hai Deewani _ Ranbir Kapoor, Deepika Padukone.wav", "Illahi"));
a2 = new Album("Jubin Nautiyal",new SongPath("C:\\Users\\PALLAVI\\Downloads\\Kaabil-Hoon-_Kaabil_-128-Kbps.wav","Kaabil hoon yaa"));
a3 = new Album("Shreya Ghoshal",new SongPath("C:\\Users\\PALLAVI\\Downloads\\02-Kar-Har-Maidaan-Fateh-Sanju.wav","Kar har maidan fateh"));
a4 = new Album("Shankar Mahadevan", new SongPath("C:\\Users\\PALLAVI\\Downloads\\Breathless-Shankar-Mahadevan.wav","Breathless"));
create(); //adds all the songs to respective albums
p1.insertEnd(a1);
p1.insertEnd(a2);
p1.insertEnd(a3);
p1.insertEnd(a4);
//p1.getHead().getData().q.getHead().getData().playSong();

}

/**
* @throws UnsupportedAudioFileException
* @throws IOException
* @throws LineUnavailableException
*
* creates the Original Playlist
*/
void create() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

a1.addSong(new SongPath("C:\\Users\\PALLAVI\\Downloads\\Deva Deva - Brahmastra _ Amitabh B _ Ranbir Kapoor _ Alia Bhatt _ Pritam _ Arijit Singh _ Amitabh.wav", "Deva Deva"));
a1.addSong(new SongPath("C:\\Users\\PALLAVI\\Downloads\\Naina - Dangal _ Aamir Khan _ Arijit Singh _ Pritam _ Amitabh Bhattacharya _ New Song 2017.wav", "Naina"));
a2.addSong(new SongPath("C:\\Users\\PALLAVI\\Downloads\\Zindagi-Kuch-Toh-Bata-_Reprise_.wav","Zindagi kuch toh" ));
a2.addSong(new SongPath("C:\\Users\\PALLAVI\\Downloads\\Raatan-Lambiyan_PagalWorld.com.se_.wav", "Raatan Lambiya"));
a3.addSong(new SongPath("C:\\Users\\PALLAVI\\Downloads\\Saibo-Shor-in-the-City-128-Kbps.wav", "Saibo"));
a3.addSong(new SongPath("C:\\Users\\PALLAVI\\Downloads\\08. Raabta (Kehte Hain Khuda Ne).wav", "Raabta"));
a4.addSong(new SongPath("C:\\Users\\PALLAVI\\Downloads\\128-Maa - Taare Zameen Par 128 Kbps.wav", "Maa"));
a4.addSong(new SongPath("C:\\Users\\PALLAVI\\Downloads\\02-Dilbaro-Raazi-320Kbps.mp3.wav", "Dilbaro"));


}

/**
* @param a Album
* @throws UnsupportedAudioFileException
* @throws IOException
* @throws LineUnavailableException
*
* Runs a single Album
*/
void runAlbum(Album a) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
MyDoublyLinkedList<SongPath>.Node curr = a.q.getHead();
int choice1 = 0,m;

Scanner ac=new Scanner(System.in);
//curr.getData().playSong();
System.out.println("Playing your favorite Album :)");
curr.getData().playSong();
curr.getData().s1.clip.close();

while(curr!=null) {
System.out.println("Select option for current album :)\n1)Next Song\n2)Previous Song\n3)Exit");
choice1 =ac.nextInt();

if(choice1 == 1) {

if(curr.getNext() == null) {
System.out.println("End of Album!");
flag = true; //next status
exitFlag = false;
return;
}
curr=curr.getNext();
curr.getData().playSong();
curr.getData().s1.clip.close();
}

else if(choice1==2) {
if(curr.getPrev()==null) {

System.out.println("No previous song in this Album!...");
flag = false; //previous status
exitFlag = false;
return;
}
else {
curr=curr.getPrev();
curr.getData().playSong();
curr.getData().s1.clip.close();
}
}
else if(choice1==3) {
exitFlag = true;
return;
}
else {
System.out.println("Enter a valid choice!");
}
}
}


/**
* @param pos Artist number
* @throws UnsupportedAudioFileException
* @throws IOException
* @throws LineUnavailableException
*
* Runs the playlist from given artist number
*/
void runPlaylist(int pos) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
MyDoublyLinkedList<Album>.Node ptr = p1.getHead();

for(int i = 0; i<pos-1 ; i++) { //sets ptr to chosen artist...ptr traverses the main LL
ptr = ptr.getNext();
}

while(ptr!=null) {
runAlbum(ptr.getData());

if (flag && !exitFlag) {
if(ptr.getNext()!=null) {
ptr=ptr.getNext();
System.out.println("Moving to next album..."+ptr.getData());
}
else{
ptr = ptr.getNext();
break;
}
}
else if (!flag && !exitFlag) {

if(ptr.getPrev()!=null) {
ptr = ptr.getPrev();
System.out.println("Moving to previous album..."+ptr.getData());
}
else {
ptr = ptr.getPrev();
break;
}
}
else if (exitFlag) {
System.out.println("Exiting from the playlist.");
return;
}
else {
System.out.println("OOPs !");
}
}

//ptr is null at this point
if(ptr==null) {
System.out.println("End of playlists!");
}
}

/**
* @param path
* @param artistName
* @param songName
* Adds new song to the selected album(Artist)
* from the given choices
*/
void addNewSong(String path, String artistName, String songName) {

SongPath newSong = new SongPath(path, songName);
int flag=0;
MyDoublyLinkedList<Album>.Node ptr = p1.getHead();

//search given artist
while(ptr!=null) {
if(ptr.getData().artist.equals(artistName))
{
flag=1; //set flag when artist found
ptr.getData().q.insertEnd(newSong);
break;
}
ptr=ptr.getNext();

}

if(flag==0) {
System.out.println("Artist not found!");
}

}

/**
* Displays list of artists and their songs
*/
void displayPlaylist() {
System.out.println("***************** M U S I F Y ******************");
System.out.println("**************************************************");

System.out.println("ARTIST  \t  SONG");
MyDoublyLinkedList<Album>.Node ptr = p1.getHead();
for (int i = 0; i<p1.getSize() ; i++) {
System.out.print((i+1)+"]");
ptr.getData().displayAlbum();  
ptr = ptr.getNext();

}
}
}
public class Main {

public static void main(String[] args) {
// TODO Auto-generated method stub
Scanner sc=new Scanner(System.in);

Playlist mySongs;
int sNo;
int choice;
String aName = "", pathName = "", sName = "";


try {
mySongs = new Playlist();
mySongs.displayPlaylist();
do {
System.out.println("***************** M U S I F Y ******************");
System.out.println("**************************************************");
System.out.println("1. Run Playlist");
System.out.println("2. Add song to Album");
System.out.println("3. Display");
System.out.println("4. Exit");
choice = sc.nextInt();
sc.nextLine();
switch(choice) {
case 1:{
int artNo = 0;
System.out.println("Enter Artist number:");
artNo = sc.nextInt();

mySongs.runPlaylist(artNo);
break;
}
case 2:{

System.out.println("Enter song no.\n1. Shayad\n2. Manike\n3. Ghar More Pardesiya\n4. Mitwa");
sNo = sc.nextInt();

switch(sNo) {
case 1:
pathName = "C:\\Users\\PALLAVI\\Downloads\\Shayad - Love Aaj Kal _ Kartik _ Sara _ Arushi _ Pritam _ Arijit Singh.wav";
sName = "Shayad";
aName = "Arijit Singh";
break;

case 2:
pathName = "C:\\Users\\PALLAVI\\Downloads\\Manike (Lyrical)_ Thank God _ Nora,Sidharth_ Tanishk,Yohani,Jubin,Surya R _ Rashmi Virag _ Bhushan K.wav";
sName = "Manike";
aName = "Jubin Nautiyal";

break;

case 3:
pathName = "C:\\Users\\PALLAVI\\Downloads\\Ghar More Pardesiya - Full Video_ Kalank _ Varun, Alia  Madhuri_ Shreya  Vaishali_ Pritam_ Amitabh.wav";
sName = "Ghar More Pardesiya";
aName = "Shreya Ghoshal";

break;

case 4:
pathName = "C:\\Users\\PALLAVI\\Downloads\\Mitwa Full Video - KANK_Shahrukh Khan,Rani Mukherjee_Shafqat Amanat Ali_Shankar Mahadevan.wav";
sName = "Mitwa";
aName = "Shankar Mahadevan";

break;

default: System.out.println("Something went wrong :(");
}

mySongs.addNewSong(pathName, aName, sName);
System.out.println("Ädded Successfully!");
}
//mySongs.displayPlaylist();
break;

case 3:{
mySongs.displayPlaylist();
break;
}
case 4:{
System.out.println("This is end");
break;
}
}
}while(choice != 4);

} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}
}

}


//--------------------------------------------------------------------------------------------------------------------------

// Java program to play an Audio
// file using Clip Object
package soundsystempackage;
//Java program to play an Audio
//file using Clip Object
import java.io.File;

import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author RADHIKA
 *class that stores path of specific song and plays it
 */
public class SimpleAudioPlayer
{

// to store current position
Long currentFrame;
public Clip clip;

// current status of clip
String status = "";

AudioInputStream audioInputStream;
String filePath = " ";

/**
* @param newFilePath
* @throws UnsupportedAudioFileException
* @throws IOException
* @throws LineUnavailableException
* Constructor to initialize streams and clip
*
*/
public SimpleAudioPlayer(String newFilePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException
{
filePath = newFilePath;
// create AudioInputStream object
audioInputStream =
AudioSystem.getAudioInputStream(new File(newFilePath).getAbsoluteFile());

// create clip reference
clip = AudioSystem.getClip();

// open audioInputStream to the clip
clip.open(audioInputStream);

clip.loop(Clip.LOOP_CONTINUOUSLY);
}

/**
* @param c choice
* @throws IOException
* @throws LineUnavailableException
* @throws UnsupportedAudioFileException
* Work as the user enters his choice
*/
public void gotoChoice(int c) throws IOException, LineUnavailableException, UnsupportedAudioFileException
{
switch (c)
{
case 1:
pause();
break;
case 2:
try {
resumeAudio();
}catch(Exception e) {
System.out.println("OOPS! You Entered something wrong.");
}
break;
case 3:
restart();
break;
case 4:
stop();
break;
}

}

/**
* Method to play the audio, sets status to play
*/
public void play()
{
//start the clip
clip.start();
status = "play";
}

/**
* Method to pause the audio
*/
public void pause()
{
if (status.equals("paused"))
{
System.out.println("audio is already paused");
return;
}
this.currentFrame = this.clip.getMicrosecondPosition();
clip.stop();
status = "paused";
}

/**
* @throws UnsupportedAudioFileException
* @throws IOException
* @throws LineUnavailableException
* Method to resume the audio
*/
public void resumeAudio() throws UnsupportedAudioFileException,
IOException, LineUnavailableException
{
if (status.equals("play"))
{
System.out.println("Audio is already "+ "being played");
return;
}
clip.close();
resetAudioStream();
clip.setMicrosecondPosition(currentFrame);
this.play();
}

/**
* @throws IOException
* @throws LineUnavailableException
* @throws UnsupportedAudioFileException
* Method to restart the audio
*/
public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException
{
clip.stop();
clip.close();
resetAudioStream();
currentFrame = 0L;
clip.setMicrosecondPosition(0);
this.play();
}

/**
* @throws UnsupportedAudioFileException
* @throws IOException
* @throws LineUnavailableException
* Method to stop the audio
*/
public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException
{
currentFrame = 0L;
clip.stop();
clip.close();
}

/**
* @param c
* @throws UnsupportedAudioFileException
* @throws IOException
* @throws LineUnavailableException
* Method to jump over a specific part
*/
public void jump(long c) throws UnsupportedAudioFileException, IOException, LineUnavailableException
{
if (c > 0 && c < clip.getMicrosecondLength())
{
clip.stop();
clip.close();
resetAudioStream();
currentFrame = c;
clip.setMicrosecondPosition(c);
this.play();
}
}

/**
* @throws UnsupportedAudioFileException
* @throws IOException
* @throws LineUnavailableException
* Method to reset audio stream
*/
public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException
{
audioInputStream = AudioSystem.getAudioInputStream(
new File(filePath).getAbsoluteFile());
clip.open(audioInputStream);
clip.loop(Clip.LOOP_CONTINUOUSLY);
}

}

//------------------------------------------------------------------------------------------------------------

package mygenericdoublylinkedlistpackage;

/**
 * @author RADHIKA
 * Generic Doubly Linked List
 * @param <T>
 */
public class MyDoublyLinkedList<T> {
private Node head;
private Node tail;
private int size;

/**
* @author RADHIKA
* class Node
*/
//--------------------------------------------------
public class Node{
private T data;
private Node next;
private Node prev;

Node(){
data = null;
next = null;
}

Node(T data){
this.data = data;
next = null;
}

/**
* @return the next
*/
public Node getNext() {
return next;
}

/**
* @param next the next to set
*/
public void setNext(Node next) {
this.next = next;
}

/**
* @return the data
*/
public T getData() {
return data;
}

/**
* @param data the data to set
*/
public void setData(T data) {
this.data = data;
}

/**
* @return the prev
*/
public Node getPrev() {
return prev;
}

/**
* @param prev the prev to set
*/
public void setPrev(Node prev) {
this.prev = prev;
}
//-----------------------------------------------------------
}

public MyDoublyLinkedList() {
head = null;
tail = null;
size = 0;
}

/**
* @param data
*  Inserts at the front
*/
public void insertFront(T data){
Node newNode = new Node(data);

if (head != null) {
newNode.next = head;
}

head = newNode;
size++;
return;
}

/**
* @param data
*  Inserts at the end
*/
public void insertEnd(T data){
Node newNode = new Node(data);

//Empty List
if (head == null) {
head = newNode;
tail = newNode;
size++;
return;
}

tail.next = newNode;
newNode.setPrev(tail);
tail = newNode;

size++;
return;
}

/**
* Prints the list
*  For data of Object type, To String method will be printed
*/
public void printList(){
if (this.isEmpty()) {
return;
}

Node curr = head;
int i = 0;
while(curr != null) {
i++;
System.out.println(" \t\t"+i+". "+curr.getData());
curr = curr.next;
}
}

/**
* @return Object data deleted
*  Deletes from front
*/
public T deleteFront() {
if (head == null) {
System.out.println("Empty List");
return null; //This can raise null pointer exception
}

Node x = head;
head = head.next;
size--;
return x.data; //return data deleted

//System.out.println("Element deleted");
}

/**
* @return Object data deleted
*  Deletes from end
*/
public T deleteEnd () {

if (head == null) {
System.out.println("Empty List");
return null; //This can raise null pointer exception
}

T deletedData = tail.getData();
tail = tail.getPrev();
tail.setNext(null);

size--;
return deletedData;

}

/**
* @param pos
* @return deleted data
* Deletes node from position pos
*/
public T deletePos(int pos) {
if (head == null) {
System.out.println("Empty List");
return null; //This can raise null pointer exception
}

if (pos > size) {
System.out.println("Invalid position");
return null;
}

Node curr = head;

for(int i=1; i<pos+1;i++) {
curr=curr.next;
}
//now curr is at pos

curr.prev=curr.next;
curr.next.prev=curr.prev;
curr.prev = null;
curr.next = null;

return curr.getData();
}


/**
* @return true if empty list
*/
public boolean isEmpty() {
return (head == null);
}

/**
* @return the head
*/
public Node getHead() {
return head;
}

/**
* @return the tail
*/
public Node getTail() {
return tail;
}

/**
* @param tail the tail to set
*/
public void setTail(Node tail) {
this.tail = tail;
}

/**
* @param head the head to set
*/
public void setHead(Node head) {
this.head = head;
}

/**
* @return the size
*/
public int getSize() {
return size;
}

/**
* @param size the size to set
*/
public void setSize(int size) {
this.size = size;
}
}

OUTPUT:

***************** M U S I F Y ******************
**************************************************
ARTIST    SONG
1]Arijit Singh
  1. Illahi
  2. Deva Deva
  3. Naina

2]Jubin Nautiyal
  1. Kaabil hoon yaa
  2. Zindagi kuch toh
  3. Raatan Lambiya

3]Shreya Ghoshal
  1. Kar har maidan fateh
  2. Saibo
  3. Raabta

4]Shankar Mahadevan
  1. Breathless
  2. Maa
  3. Dilbaro

***************** M U S I F Y ******************
**************************************************
1. Run Playlist
2. Add song to Album
3. Display
4. Exit
1
Enter Artist number:
3
Playing your favorite Album :)
Song Options:
Stop the song to go to the previous or next one
1. PAUSE
2. RESUME
3. RESTART
4. STOP
4
Select option for current album :)
1)Next Song
2)Previous Song
3)Exit
3
Exiting from the playlist.
***************** M U S I F Y ******************
**************************************************
1. Run Playlist
2. Add song to Album
3. Display
4. Exit
2
Enter song no.
1. Shayad
2. Manike
3. Ghar More Pardesiya
4. Mitwa
3
Ädded Successfully!
***************** M U S I F Y ******************
**************************************************
1. Run Playlist
2. Add song to Album
3. Display
4. Exit