head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OrderStatusDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3OrderStatusDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/20 ��ؔ��R�I(SRA)�@@�V�K�쐬
*/
package webbroker3.xbruito.define;

/**
 * ������ԋ敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author ��ؔ��R�I
 * @@version 1.0
 */
public interface WEB3OrderStatusDivDef
{
    /**
     * 1 : ��t�ρi�V�K�����j
     */
    public static final String ORDERING = "1";

    /**
     * 6 : �������s�i�V�K�����j
     */
    public static final String ORDER_ERROR = "6";

    /**
     * 12 : ��t�ρi��������j
     */
    public static final String ORDER_CANCELING = "12";
    
    /**
     * 14 : �����ρi��������j
     */
    public static final String ORDER_CANCELED = "14";

    /**
     * 15 : �������s�i��������j 
     */
    public static final String ORDER_CANCEL_FAILED = "15";
    
    /**
     * 30 : ��t�ρiMRF��񂠂�j
     */
    public static final String ORDERING_MRF_SELL = "30";
    
    /**
     * 31 : �����ρiMRF��񂠂�j
     */
    public static final String ORDER_MRF_SELL = "31";
    
    /**
     * 32 : �������s�i�l�q�e����������j
     */
    public static final String CANCEL_MRF_SELL_FAILED = "32";
    
	/**
	 * 56 : ����s��    
	 */
	public static final String CANCEL_DISABLE = "56";
}
@
