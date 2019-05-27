package chess;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
//======================================================Don't modify below===============================================================//
enum PieceType {king, queen, bishop, knight, rook, pawn, none}
enum PlayerColor {black, white, none}
	
public class ChessBoard {
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JPanel chessBoard;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private Piece[][] chessBoardStatus = new Piece[8][8];
	private ImageIcon[] pieceImage_b = new ImageIcon[7];
	private ImageIcon[] pieceImage_w = new ImageIcon[7];
	private JLabel message = new JLabel("Enter Reset to Start");

	ChessBoard(){
		initPieceImages();
		initBoardStatus();
		initializeGui();
	}
	
	public final void initBoardStatus(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) chessBoardStatus[j][i] = new Piece();
		}
	}
	
	public final void initPieceImages(){
		pieceImage_b[0] = new ImageIcon(new ImageIcon("./img/king_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[1] = new ImageIcon(new ImageIcon("./img/queen_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[2] = new ImageIcon(new ImageIcon("./img/bishop_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[3] = new ImageIcon(new ImageIcon("./img/knight_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[4] = new ImageIcon(new ImageIcon("./img/rook_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[5] = new ImageIcon(new ImageIcon("./img/pawn_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
		
		pieceImage_w[0] = new ImageIcon(new ImageIcon("./img/king_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[1] = new ImageIcon(new ImageIcon("./img/queen_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[2] = new ImageIcon(new ImageIcon("./img/bishop_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[3] = new ImageIcon(new ImageIcon("./img/knight_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[4] = new ImageIcon(new ImageIcon("./img/rook_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[5] = new ImageIcon(new ImageIcon("./img/pawn_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	}
	
	public ImageIcon getImageIcon(Piece piece){
		if(piece.color.equals(PlayerColor.black)){
			if(piece.type.equals(PieceType.king)) return pieceImage_b[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_b[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_b[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_b[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_b[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_b[5];
			else return pieceImage_b[6];
		}
		else if(piece.color.equals(PlayerColor.white)){
			if(piece.type.equals(PieceType.king)) return pieceImage_w[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_w[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_w[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_w[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_w[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_w[5];
			else return pieceImage_w[6];
		}
		else return pieceImage_w[6];
	}

	public final void initializeGui(){
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
	    JToolBar tools = new JToolBar();
	    tools.setFloatable(false);
	    gui.add(tools, BorderLayout.PAGE_START);
	    JButton startButton = new JButton("Reset");
	    startButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		initiateBoard();
	    	}
	    });
	    
	    tools.add(startButton);
	    tools.addSeparator();
	    tools.add(message);

	    chessBoard = new JPanel(new GridLayout(0, 8));
	    chessBoard.setBorder(new LineBorder(Color.BLACK));
	    gui.add(chessBoard);
	    ImageIcon defaultIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	    Insets buttonMargin = new Insets(0,0,0,0);
	    for(int i=0; i<chessBoardSquares.length; i++) {
	        for (int j = 0; j < chessBoardSquares[i].length; j++) {
	        	JButton b = new JButton();
	        	b.addActionListener(new ButtonListener(i, j));
	            b.setMargin(buttonMargin);
	            b.setIcon(defaultIcon);
	            if((j % 2 == 1 && i % 2 == 1)|| (j % 2 == 0 && i % 2 == 0)) b.setBackground(Color.WHITE);
	            else b.setBackground(Color.gray);
	            b.setOpaque(true);
	            b.setBorderPainted(false);
	            chessBoardSquares[j][i] = b;
	        }
	    }

	    for (int i=0; i < 8; i++) {
	        for (int j=0; j < 8; j++) chessBoard.add(chessBoardSquares[j][i]);
	        
	    }
	}

	public final JComponent getGui() {
	    return gui;
	}
	
	public static void main(String[] args) {
	    Runnable r = new Runnable() {
	        @Override
	        public void run() {
	        	ChessBoard cb = new ChessBoard();
                JFrame f = new JFrame("Chess");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setResizable(false);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
	}
		
			//================================Utilize these functions========================================//	
	
	class Piece{
		PlayerColor color;
		PieceType type;
		
		Piece(){
			color = PlayerColor.none;
			type = PieceType.none;
		}
		Piece(PlayerColor color, PieceType type){
			this.color = color;
			this.type = type;
		}
	}
	
	public void setIcon(int x, int y, Piece piece){
		chessBoardSquares[y][x].setIcon(getImageIcon(piece));
		chessBoardStatus[y][x] = piece;
	}
	
	public Piece getIcon(int x, int y){
		return chessBoardStatus[y][x];
	}
	
	public void markPosition(int x, int y){
		chessBoardSquares[y][x].setBackground(Color.pink);
	}
	
	public void unmarkPosition(int x, int y){
		if((y % 2 == 1 && x % 2 == 1)|| (y % 2 == 0 && x % 2 == 0)) chessBoardSquares[y][x].setBackground(Color.WHITE);
		else chessBoardSquares[y][x].setBackground(Color.gray);
	}
	
	public void setStatus(String inpt){
		message.setText(inpt);
	}
	
	public void initiateBoard(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) setIcon(i, j, new Piece());
		}
		setIcon(0, 0, new Piece(PlayerColor.black, PieceType.rook));
		setIcon(0, 1, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 2, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 3, new Piece(PlayerColor.black, PieceType.queen));
		setIcon(0, 4, new Piece(PlayerColor.black, PieceType.king));
		setIcon(0, 5, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 6, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 7, new Piece(PlayerColor.black, PieceType.rook));
		for(int i=0;i<8;i++){
			setIcon(1, i, new Piece(PlayerColor.black, PieceType.pawn));
			setIcon(6, i, new Piece(PlayerColor.white, PieceType.pawn));
		}
		setIcon(7, 0, new Piece(PlayerColor.white, PieceType.rook));
		setIcon(7, 1, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 2, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 3, new Piece(PlayerColor.white, PieceType.queen));
		setIcon(7, 4, new Piece(PlayerColor.white, PieceType.king));
		setIcon(7, 5, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 6, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 7, new Piece(PlayerColor.white, PieceType.rook));
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) unmarkPosition(i, j);
		}
		onInitiateBoard();
	}
//======================================================Don't modify above==============================================================//	




//======================================================Implement below=================================================================//		
	enum MagicType {MARK, CHECK, CHECKMATE};
	private int selX, selY;
	private boolean check, checkmate, end;
	int turn=1;
	int change=0;
	int len=0;
	int mark1=0;
	int mark2=0;
	int markk=0;
	int markb=0;
	int markr=0;
	int markq=0;
	int marking=0;
	int [] m = new int[100];
	int []prevposition = new int[2]; 
	
	class ButtonListener implements ActionListener{
		int x;
		int y;
		ButtonListener(int x, int y){
			this.x = x;
			this.y = y;
		}
		public void unmark(int marker)
		{
			int i=0;
			for(i=0; i<len; i=i+2)
			{
				unmarkPosition(m[i], m[i+1]);					
				if(x==m[i] && y==m[i+1]) 
				{
					setIcon(m[i],m[i+1], getIcon(prevposition[0],prevposition[1]));
					setIcon(prevposition[0], prevposition[1], new Piece());
					if(turn==1) turn=0;
					else turn=1;
				}
			}
			marker=0;
			len=0;
		}
		public void mark(int x, int y, PlayerColor col, PlayerColor excol)
		{
			int pawninit=0;
			int con=0;
			if(col == PlayerColor.black) { pawninit=1; con=1; }
			else { pawninit = 6; con=-1; }
			
			if(mark2==1) {unmark(mark2); mark2=0;}
			else if (mark1==1) {unmark(mark1); mark1=0;}
			else if(markk==1) {unmark(markk); markk=0;}
			else if(markb==1) {unmark(markb); markb=0;}
			else if(markr==1) {unmark(markr); markr=0;}
			else if(markq==1) {unmark(markq); markq=0;}
			else if(marking==1) {unmark(marking); marking=0;}
			else if(getIcon(x,y).color==col && getIcon(x,y).type==PieceType.pawn )
			{
				int i=0;
				prevposition[0]=x;
				prevposition[1]=y;
				if( x+con<8 && x+con>=0 )
				{
					mark1=1;
					if(getIcon(x+con,y).color==PlayerColor.none )
					{
						markPosition(x+con,y);
						m[i]=x+con;
						i++;
						m[i]=y;
						i++;
						if(x+2*con<8)
						{
							if(getIcon(x+2*con,y).color== PlayerColor.none && x==pawninit )
							{
								markPosition(x+2*con,y);
								mark1=0;
								mark2=1;
								m[i]=x+2*con;
								i++;
								m[i]=y;
								i++;
							}
						}						
					}
					if(y+1<8)
					{
						if(getIcon(x+con,y+1).color == excol)
						{
							markPosition(x+con,y+1);
							m[i]=x+con;
							i++;
							m[i]=y+1;
							i++;
						}
					}
					if(y-1>=0)
					{
						if(getIcon(x+con,y-1).color == excol)
						{
							markPosition(x+con,y-1);
							m[i]=x+con;
							i++;
							m[i]=y-1;
							i++;
						}
					}
				}
				len = i;
			}
			else if(getIcon(x,y).color==col && getIcon(x,y).type==PieceType.knight)
			{
				int i=0;
				prevposition[0]=x;
				prevposition[1]=y;
				len=0;
				if(x+2<8 && y+1<8)
				{	
					if(getIcon(x+2,y+1).color!=col )
					{
						markPosition(x+2,y+1);
						m[i]=x+2;
						i++;
						m[i]=y+1;
						i++;
					}
				}
				if(x+2<8 && y-1>=0)
				{
					if(getIcon(x+2,y-1).color!=col )
					{
						markPosition(x+2,y-1);
						m[i]=x+2;
						i++;
			        	m[i]=y-1;
			        	i++;
					}
				}
				if(x-2>=0 && y+1<8)
				{
					if(getIcon(x-2,y+1).color!=col )
					{
						markPosition(x-2,y+1);
						m[i]=x-2;
						i++;
						m[i]=y+1;
						i++;
					}
				}
				if(x-2>=0 && y-1>=0)
				{	
					if(getIcon(x-2,y-1).color!=col )
					{
						markPosition(x-2,y-1);
						m[i]=x-2;
						i++;
						m[i]=y-1;
			        	i++;
					}
				}
				if(x+1<8 && y+2<8)
				{	
					if(getIcon(x+1,y+2).color!=col )
					{
						markPosition(x+1,y+2);
						m[i]=x+1;
						i++;
						m[i]=y+2;
						i++;
					}
				}
				if(x-1>=0 && y+2<8)
				{
					if(getIcon(x-1,y+2).color!=col )
					{
						markPosition(x-1,y+2);
						m[i]=x-1;
						i++;
						m[i]=y+2;
						i++;
					}
				}
				if(x+1<8 && y-2>=0)
				{	
					if(getIcon(x+1,y-2).color!=col )
					{
						markPosition(x+1,y-2);
						m[i]=x+1;
						i++;
						m[i]=y-2;
			        	i++;
					}
				}
				if(x-1>=0 && y-2>=0)
				{
					if(getIcon(x-1,y-2).color!=col)
					{
						markPosition(x-1,y-2);
						m[i]=x-1;
						i++;
						m[i]=y-2;
						i++;
					}
				}
				if(i!=0) markk=1;
				len=i;
			}
			else if(getIcon(x,y).color==col && getIcon(x,y).type==PieceType.bishop )
			{
				int i=0;
				int j=1;
				len=0;
				prevposition[0]=x;
				prevposition[1]=y;
				while(x+j<8 && y+j<8)
				{
					if(getIcon(x+j,y+j).color!=col )
					{
						if(getIcon(x+j,y+j).type!=PieceType.none && getIcon(x+j,y+j).color==excol && getIcon(x+j-1,y+j-1).color==excol) break;
						markPosition(x+j,y+j);
						m[i]=x+j;
						i++;
						m[i]=y+j;
						i++;						
					}
					else break;
					j++;
				}
				j=1;
				while(x-j>=0 && y+j<8)
				{
					if(getIcon(x-j,y+j).color!=col )
					{
						if(getIcon(x-j,y+j).type!=PieceType.none && getIcon(x-j,y+j).color==excol && getIcon(x-j+1,y+j-1).color==excol) break;
						markPosition(x-j,y+j);
						m[i]=x-j;
						i++;
						m[i]=y+j;
						i++;
					}
					else break;
					j++;
				}
				j=1;
				while(x-j>=0 && y-j>=0)
				{
					if(getIcon(x-j,y-j).color!=col)
					{
						if(getIcon(x-j,y-j).type!=PieceType.none && getIcon(x-j,y-j).color==excol && getIcon(x-j+1,y-j+1).color==excol) break;
						markPosition(x-j,y-j);
						m[i]=x-j;
						i++;
						m[i]=y-j;
						i++;
					}
					else break;
					j++;
				}
				j=1;
				while(x+j<8 && y-j>=0)
				{
					if(getIcon(x+j,y-j).color!=col )
					{
						if(getIcon(x+j,y-j).type!=PieceType.none && getIcon(x+j,y-j).color==excol && getIcon(x+j-1,y-j+1).color==excol) break;
						markPosition(x+j,y-j);
						m[i]=x+j;
						i++;
						m[i]=y-j;
						i++;
					}
					else break;
					j++;
				}
				if(i!=0) markb=1;
				len=i;
				
			}
			else if(getIcon(x,y).color==col && getIcon(x,y).type==PieceType.rook)
			{
				int i=0;
				int j=1;
				len=0;
				prevposition[0]=x;
				prevposition[1]=y;
				while(x+j<8)
				{
					if(getIcon(x+j,y).color!=col )
					{
						if(getIcon(x+j,y).type!=PieceType.none && getIcon(x+j,y).color==excol && getIcon(x+j-1,y).color==excol) break;
						markPosition(x+j,y);
						m[i]=x+j;
						i++;
						m[i]=y;
						i++;
					}
					else break;
					j++;
				}
				j=1;
				while(x-j>=0)
				{
					if(getIcon(x-j,y).color!=col )
					{
						if(getIcon(x-j,y).type!=PieceType.none && getIcon(x-j,y).color==excol && getIcon(x-j-1,y).color==excol) break;
						markPosition(x-j,y);
						m[i]=x-j;
						i++;
						m[i]=y;
						i++;
					}
					else break;
					j++;
				}
				j=1;
				while(y+j<8)
				{
					if(getIcon(x,y+j).color!=col )
					{
						if(getIcon(x,y+j).type!=PieceType.none && getIcon(x,y+j).color==excol && getIcon(x,y+j-1).color==excol) break;
						markPosition(x,y+j);
						m[i]=x;
						i++;
						m[i]=y+j;
						i++;
					}
					else break;
					j++;
				}
				j=1;
				while(y-j>=0)
				{
					if(getIcon(x,y-j).color!=col )
					{
						if(getIcon(x,y-j).type!=PieceType.none && getIcon(x,y-j).color==excol && getIcon(x,y+j-1).color==excol) break;
						markPosition(x,y-j);
						m[i]=x;
						i++;
						m[i]=y-j;
						i++;
					}
					else break;
					j++;
				}
				if(i!=0) markr=1;
				len=i;
			}
			else if(getIcon(x,y).color==col && getIcon(x,y).type==PieceType.queen)
			{
				int i=0;
				int j=1;
				len=0;
				prevposition[0]=x;
				prevposition[1]=y;
				while(x+j<8)
				{
					if(getIcon(x+j,y).color!=col )
					{
						if(getIcon(x+j,y).type!=PieceType.none && getIcon(x+j,y).color==excol && getIcon(x+j-1,y).color==excol) break;
						markPosition(x+j,y);
						m[i]=x+j;
						i++;
						m[i]=y;
						i++;
					}
					else break;
					j++;
				}
				j=1;
				while(x-j>=0)
				{
					if(getIcon(x-j,y).color!=col )
					{
						if(getIcon(x-j,y).type!=PieceType.none && getIcon(x-j,y).color==excol && getIcon(x-j-1,y).color==excol) break;
						markPosition(x-j,y);
						m[i]=x-j;
						i++;
						m[i]=y;
						i++;
					}
					else break;
					j++;
				}
				j=1;
				while(y+j<8)
				{
					if(getIcon(x,y+j).color!=col )
					{
						if(getIcon(x,y+j).type!=PieceType.none && getIcon(x,y+j).color==excol && getIcon(x,y+j-1).color==excol) break;
						markPosition(x,y+j);
						m[i]=x;
						i++;
						m[i]=y+j;
						i++;
					}
					else break;
					j++;
				}
				j=1;
				while(y-j>=0)
				{
					if(getIcon(x,y-j).color!=col )
					{
						if(getIcon(x,y-j).type!=PieceType.none && getIcon(x,y-j).color==excol && getIcon(x,y+j-1).color==excol) break;
						markPosition(x,y-j);
						m[i]=x;
						i++;
						m[i]=y-j;
						i++;
					}
					else break;
					j++;
				}
				j=1;
				while(x+j<8 && y+j<8)
				{
					if(getIcon(x+j,y+j).color!=col )
					{
						if(getIcon(x+j,y+j).type!=PieceType.none && getIcon(x+j,y+j).color==excol && getIcon(x+j-1,y+j-1).color==excol) break;
						markPosition(x+j,y+j);
						m[i]=x+j;
						i++;
						m[i]=y+j;
						i++;						
					}
					else break;
					j++;
				}
				j=1;
				while(x-j>=0 && y+j<8)
				{
					if(getIcon(x-j,y+j).color!=col )
					{
						if(getIcon(x-j,y+j).type!=PieceType.none && getIcon(x-j,y+j).color==excol && getIcon(x-j+1,y+j-1).color==excol) break;
						markPosition(x-j,y+j);
						m[i]=x-j;
						i++;
						m[i]=y+j;
						i++;
					}
					else break;
					j++;
				}
				j=1;
				while(x-j>=0 && y-j>=0)
				{
					if(getIcon(x-j,y-j).color!=col)
					{
						if(getIcon(x-j,y-j).type!=PieceType.none && getIcon(x-j,y-j).color==excol && getIcon(x-j+1,y-j+1).color==excol) break;
						markPosition(x-j,y-j);
						m[i]=x-j;
						i++;
						m[i]=y-j;
						i++;
					}
					else break;
					j++;
				}
				j=1;
				while(x+j<8 && y-j>=0)
				{
					if(getIcon(x+j,y-j).color!=col )
					{
						if(getIcon(x+j,y-j).type!=PieceType.none && getIcon(x+j,y-j).color==excol && getIcon(x+j-1,y-j+1).color==excol) break;
						markPosition(x+j,y-j);
						m[i]=x+j;
						i++;
						m[i]=y-j;
						i++;
					}
					else break;
					j++;
				}
				if(i!=0) markq=1;
				len=i;
				
			}
			else if(getIcon(x,y).color==col && getIcon(x,y).type==PieceType.king)
			{
				int l=0;
				prevposition[0]=x;
				prevposition[1]=y;
				for(int i=-1; i<=1; i++)
				{
					for(int j=-1; j<=1; j++)
					{
						if(x+i>=0 && x+i<8 && y+j>=0 && y+j<8)
						{
							if(getIcon(x+i,y+j).color!=col)
							{
								markPosition(x+i,y+j);
								m[l]=x+i;
								l++;
								m[l]=y+j;
								l++;
							}
						}
					}
				}
				len=l;
				if(len>0) marking=1;
			}
		}

		public void actionPerformed(ActionEvent e) {	// Only modify here
			// (x, y) is where the click event occured
			
			if(turn==1) mark(x,y, PlayerColor.black, PlayerColor.white);		
			else mark(x,y, PlayerColor.white, PlayerColor.black);
			
			if(turn==1) setStatus("BLACK'S TURN");
			else  setStatus("WHITE'S TURN");


		}
			
			
			
	}
	
	
	void onInitiateBoard(){
		setStatus("BLACK'S TURN");
	}
}