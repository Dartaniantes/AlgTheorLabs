package Lab_2;

import java.util.LinkedList;

public class HashTable {
    Node[] nodes;
    int size;
    LinkedList<Node>[] collisionTab;

    public HashTable(int size) {
        this.size = size;
        nodes = new Node[size];
    }

    public HashTable(int size, String[][] stuff) {
        this.size = size;
        nodes = new Node[size];
        fillUpHashTable(stuff);
    }

    public void put(String key, String value) {
        int index = PJWHash(key);
        Node newNode = new Node(key,value,index);
        if (nodes[index] == null) {
            nodes[index] = newNode;
        } else {
            if (collisionTab == null) {
                collisionTab = new LinkedList[size];
                collisionTab[index].add(0,newNode);
                collisionTab[index].peek();
            } else {
                collisionTab[index].add(newNode);
            }
        }
    }

    public Node get(String key) {
        int index = PJWHash(key);
        Node temp;
        if(nodes[index].key == key)
            return nodes[index];
        else
            if ((temp = getFromList(collisionTab[index], key)) != null)
                return temp;
            else {
                System.out.println("Hash table doesn't contain node with key '" + key + "'");
                return null;
            }
    }

    private Node getFromList(LinkedList<Node> list, String key) {
        for (Node node: list)
            if(node.key == key)
                return node;
        return null;
    }


    private int PJWHash(String key) {
        int hash = 0;
        int test;

        for (int i = 0; i < key.length(); i++) {
            hash = (hash << 4) + key.charAt(i);
            if((test = hash & 0xf0000000) != 0)
                hash = ((hash ^ (test >> 24))) & (0xfffffff);
        }
        return hash % size;
    }

    public void fillUpHashTable(String[][] stuff) {
        for (int i = 0; i < stuff.length; i++)
            put(stuff[i][0], stuff[i][1]);
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
            return "Key '" + key + "' contains value '" + value;
        }

    }
}



