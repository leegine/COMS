head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.38.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMessageTradingTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���b�Z�[�W�p����敪�萔��`�C���^�t�F�C�X(WEB3ToSuccMessageTradingTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13  �s�p(sinocom)�@@�V�K�쐬
*/
package webbroker3.triggerorder.define;

/**
 * �����\�񒍕��P��Impl�̃��b�Z�[�W�p����敪�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author �s�p
 * @@version 1.1
 */
public interface WEB3ToSuccMessageTradingTypeDef
{
    /**
     * 1 : ����������
     */
    public static final String BUY_ORDER = "1";

    /**
     * 2 : ����������
     */
    public static final String SELL_ORDER = "2";
    
    /**
     * 3 : �V�K��������
     */
    public static final String OPEN_LONG_MARGIN = "3";

    /**
     * 4 : �V�K��������
     */
    public static final String OPEN_SHORT_MARGIN = "4";
    
    /**
     * 5 : �����ԍϒ����i���ԍρj
     */
    public static final String CLOSE_LONG_MARGIN = "5";
    
    /**
     * 6 : �����ԍϒ����i���ԍρj
     */
    public static final String CLOSE_SHORT_MARGIN = "6";
    
    /**
     * 7 : ��������
     */
    public static final String  SWAP_MARGIN_LONG= "7";
    
    /**
     * 8 : ���n����
     */ 
    public static final String SWAP_MARGIN_SHORT = "8";
}
@
