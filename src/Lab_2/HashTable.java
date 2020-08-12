package Lab_2;

import java.util.LinkedList;
import java.util.Scanner;

public class HashTable {
    Node[] nodes;
    int size;
    LinkedList<Node>[] collisionTab = null;
    int compareCounter = 0;
    int collisionCounter;
    String[][] stuff;

    public HashTable(int size) {
        this.size = size;
        nodes = new Node[size];
    }

    public HashTable(int size, int stuffSize) {
        this.size = size;
        nodes = new Node[size];
        fillUpHashTable(stuffSize);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hashtable #1:");
        HashTable tab = new HashTable(5000, 5000);
        tab.showGeneratedStuff();
        String input = "";
        while (true) {
            System.out.print("Enter key to get --> ");
            input = sc.nextLine();
            System.out.println();
            if(input.compareTo("-1") == 0) break;
            tab.get(input);
        }
    }

    public void put(String key, String value) {
        int index = PJWHash(key);
        Node newNode = new Node(key,value,index);
        if (nodes[index] == null) {
            nodes[index] = newNode;
        } else {
            if (collisionTab == null) {
                collisionTab = new LinkedList[size];
                collisionTab[index] = new LinkedList<>();
                collisionTab[index].add(newNode);
                collisionCounter++;
            } else {
                if (collisionTab[index] == null){
                    collisionTab[index] = new LinkedList<>();
                    collisionTab[index].add(newNode);
                    collisionCounter++;
                } else {
                  collisionTab[index].add(newNode);
                  collisionCounter++;
                }

            }
            System.out.println("Collision #" + collisionCounter);
        }
    }

    public Node get(String key) {
        int index = PJWHash(key);
        Node temp;
        if(nodes[index] != null && nodes[index].key.equals(key)){
            System.out.println("It took no comparisons to find key '" + key +"'");
            System.out.println("Founded : " + nodes[index]);
            return nodes[index];
        } else {
            if(collisionTab[index] == null){
                System.out.println("Hash table doesn't contain node with key '" + key + "'");
                return null;
            }
            if ((temp = getFromList(collisionTab[index], key)) != null){
                System.out.println(compareCounter + " compares needed to find key '" + key + "' under collisioned index" );
                System.out.println("Founded: " + temp);
                compareCounter = 0;
                return temp;
            } else {
                System.out.println("Hash table doesn't contain node with key '" + key + "'");
                return null;
            }
        }
    }

    private Node getFromList(LinkedList<Node> list, String key) {
        Node temp;
        for (int i = 0; i < list.size(); i++) {
            compareCounter++;
            if(list.get(i).key.equals(key))
                return list.get(i);
        }
        return null;
    }


    private int PJWHash(String key) {
        int hash = 0;
        int test;
        int index;

        for (int i = 0; i < key.length(); i++) {
            hash = (hash << 4) + key.charAt(i);
            if((test = hash & 0xf0000000) != 0)
                hash = ((hash ^ (test >> 24))) & (0xfffffff);
        }
        index = hash % size;
        return index;
    }

    public void fillUpHashTable(int stuffSize) {
        makeStuff(stuffSize);
        for (int i = 0; i < stuffSize; i++) {
            put(stuff[i][0], stuff[i][1]);
        }
    }

    public void makeStuff(int stuffSize) {
        StringGenerator gen = new StringGenerator();
        String[][] stuff = new String[stuffSize][2];
        String key, value;
        for (int i = 0; i < stuffSize; i++) {
            key = gen.generateString(20);
            value = gen.generateString(200);
            stuff[i][0] = key;
            stuff[i][1] = value;
        }
        this.stuff = stuff;
    }

    public String[][] getGeneratedStuff() {
        return stuff;
    }

    public void showGeneratedStuff() {
        System.out.println("Generated stuff:");
        for (int i = 0; i < size; i++) {
            System.out.println("Key : '" + stuff[i][0] + "', value : '" + stuff[i][1] + "'");
        }
    }

    class Node{
        private String key;
        private String value;
        private int index;

        public Node(String key, String value, int index) {
            this.key = key;
            this.value = value;
            this.index = index;
        }

        public String toString() {
            return "key '" + key + "' contains value '" + value + "'";
        }

    }
}



