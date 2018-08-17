package SystemDesigning;

import java.util.Scanner;
import java.util.Stack;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 8/20/2016
 */

/*In this challenge, you must implement a simple text editor. Initially, your editor contains an empty string, S.
You must perform Q operations of the following types:

        append(W) - Append string W to the end of S.
        delete(K) - Delete the last K characters of S.
        print(K) - Print the Kth character of S.
        undo() - Undo the last (not previously undone) operation of type 1 or 2, reverting S to the state it was in prior to that operation.*/

public class TextEditor {

    private StringBuilder string;
    private final Stack<StringBuilder> restoreStack;

    private TextEditor() {
        string = new StringBuilder();
        restoreStack = new Stack<>();
    }

    private void append(String W) {
        restoreStack.push(new StringBuilder(string));
        string.append(W);
    }

    private void delete(int K) {
        if (string.length() != 0) {
            restoreStack.push(new StringBuilder(string));
            string.delete(string.length() - K, string.length());
        }
    }

    private void print(int K) {
        if (string.length() != 0)
            System.out.println(string.charAt(K - 1));
    }

    private void undo() {
        if (!restoreStack.empty())
            string = restoreStack.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TextEditor textEditor = new TextEditor();
        int q = sc.nextInt();
        while (q > 0) {
            int operation = sc.nextInt();
            switch (operation) {
                case 1:
                    String W = sc.next();
                    textEditor.append(W);
                    break;
                case 2:
                    int K = sc.nextInt();
                    textEditor.delete(K);
                    break;
                case 3:
                    int M = sc.nextInt();
                    textEditor.print(M);
                    break;
                case 4:
                    textEditor.undo();
                    break;
            }
            --q;
        }
    }
}