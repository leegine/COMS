head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TradeHistoryProductDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������ꗗ���N�G�X�g(WEB3TradeHistoryProductDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25  �� �� �@@(���u) �V�K�쐬
                   2006/10/19  �����F (���u) ���f�� 057
*/
package webbroker3.tradehistory.define;

/**
 * ���i�敪 �萔��`�C���^�t�F�C�X
 * 
 * @@author �� �� �@@
 * @@version 1.0
 */
public interface WEB3TradeHistoryProductDivDef
{

    /**
     *A�F�@@�S���i
     */
    public final static String ALL_PRODUCT = "A";

    /**
     *B�F�@@�����E�M�p
     */
    public final static String EQUITY_MARGIN = "B";

    /**
     *C�F�@@����
     */
    public final static String EQUITY = "C";
    
    /**
     *D�F�@@�M�p
     */
    public final static String MARGIN = "D";

    /**
     * E�F�@@�敨�E�I�v�V����
     */
    public final static String FUTURES_OPTION = "E";
        
    /** 
     * F�F�@@�敨
     */
    public final static String FUTURES = "F";
            
    /** 
     * G�F�@@�I�v�V����
     */     
    public final static String OPTION = "G";  
      
    /**
     * H�F�@@���M�E�ݓ�
     */
    public final static String MUTUAL_FUND_RUITO = "H" ;
    
    /**
     * I�F�@@���o��<BR>
     */
    public final static String AIO = "I" ; 
    
    /**
     * J�F�@@���n�v��<BR>
     */
    public final static String CPITAL_GAIN_TAX = "J" ;
    
    /**
     * K�F�@@�O������<BR>
     */
    public final static String FOREIGN = "K" ;

    /**
     * L�F�@@��<BR>
     */
    public final static String BOND = "L";
}
@
