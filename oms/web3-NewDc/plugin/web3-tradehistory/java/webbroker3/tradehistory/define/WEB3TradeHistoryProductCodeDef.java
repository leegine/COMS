head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.45.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TradeHistoryProductCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3TradeHistoryProductCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/28  �͌d��(sinocom)�@@�V�K�쐬
                   2006/04/25 ������(���u) �d�l�ύX�E���f��048 
*/
package webbroker3.tradehistory.define;

/**
 *���i�R�[�h �萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author �͌d��
 * @@version 1.0
 */
public interface WEB3TradeHistoryProductCodeDef
{

    /**
     * 10�F��������
     */
    public static final String DOMESTIC_STOCK = "10";

    /**
     * 40�F�O������
     */
    public static final String  FOREIGN_STOCK = "40";
    
    /**
     * 11�F�M�p
     */
    public static final String  MARGIN = "11";

    /**
     * 10�F����
     */
    public static final String  EQUITY = "10";    
    
    /**
     *30�F������
     */
    public static final String  DOMESTIC_BOND = "30";    
    
    /**
     * 99�F���K
     */
    public static final String  MONEY = "99";
}@
