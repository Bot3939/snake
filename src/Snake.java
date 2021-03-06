import java.util.ArrayList;
import java.util.List;

class Snake {
    List<int[]> parts = new ArrayList<>();
    int FATNESS;
    char FACING = 'S';
    private static int[] previous_tail;

    Snake(int length, int fatness){
        FATNESS = fatness;
        for(int i = 0; i < length; i++){
            parts.add(new int[]{fatness*i, fatness*i*3});
        }
    }

    void move_forward(){
        previous_tail = parts.get(0);
        parts.remove(0); //remove the tail

        //append head based on the direction that the snake's facing
        int[] head_pos = parts.get(parts.size()-1);
        switch(FACING){
            case 'N': //up
                parts.add(new int[]{head_pos[0], head_pos[1]-FATNESS});
                break;
            case 'S': //down
                parts.add(new int[]{head_pos[0], head_pos[1]+FATNESS});
                break;
            case 'E': //right
                parts.add(new int[]{head_pos[0]+FATNESS, head_pos[1]});
                break;
            case 'W': //left
                parts.add(new int[]{head_pos[0]-FATNESS, head_pos[1]});
                break;
        }

        head_pos = parts.get(parts.size()-1);
        for(int i = 0; i < parts.size()-2; i++){
            if(parts.get(i)[0] == head_pos[0] && parts.get(i)[1] == head_pos[1]){
                System.out.println("Game Over");
                System.exit(0);
            }
        }
    }

    boolean check_collision(int[] apple){
        int[] head_pos = parts.get(parts.size()-1);
        return apple[0] == head_pos[0] && apple[1] == head_pos[1];
    }

    boolean check_collision(List<int[]> border){
        int[] head_pos = parts.get(parts.size()-1);
        for(int i = 0; i < border.size()-1; i++){
            if(head_pos[0] == border.get(i)[0] && head_pos[1] == border.get(i)[1]){
                return true;
            }
        }
        return false;
    }

    void increase_size(){
        parts.add(0, previous_tail);
    }
}
