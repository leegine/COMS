head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.47.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioOrderTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������ʒ萔��`(WEB3AdminAioOrderTypeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/03 �����q (���u) �V�K�쐬�@@�d�l�ύX���f�� NO.693
*/
package webbroker3.aio.define;

/**
 * ������ʒ萔��`
 *                                                                     
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminAioOrderTypeDef
{
    /**
     * 000�F�S��
     */
    public static final String ALL = "000";
    
    /**
     * 100�F�����S��
     */
    public static final String CASHIN_ALL = "100";
    
    /**
     * 101�FSONAR����
     */
    public static final String SONAR_CASHIN = "101";
    
    /**
     * 102�F�o�[�`��������
     */
    public static final String VIRTUAL_CASHIN = "102";
    
    /**
     * 103�F�l�b�g����
     */
    public static final String NET_CASHIN = "103";
    
    /**
     * 104�F�U�ցi����؋�������a����j
     */
    public static final String CASHIN_TRANSFER_DEPOSIT_TO_MARGIN = "104";
    
    /**
     * 105�F�ב֕ۏ؋��U�ցi�ב֕ۏ؋�����a����j
     */
    public static final String CASHIN_FX_GUARANTY_TO_DESPOSIT= "105";
    
    /**
     * 106�F���̑��U�ցiX����a����j
     */
    public static final String CASHIN_OTHER_X_TO_ACCOUNT_BALANCE = "106";
    
    /**
     * 200�F�o���S��
     */
    public static final String CASHOUT_ALL = "200";
    
    /**
     * 201�F�o��
     */
    public static final String CASHOUT = "201";
    
    /**
     * 202�F�U�ցi�a������犔��؋����j
     */
    public static final String CASHOUT_TRANSFER_DEPOSIT_TO_MARGIN = "202";
    
    /**
     * 203�F�ב֕ۏ؋��U�ցi�a�������ב֕ۏ؋��j
     */
    public static final String CASHOUT_FX_GUARANTY_TO_DESPOSIT = "203";
    
    /**
     * 204�F���̑��U�ցi�a�������X)
     */
    public static final String CASHOUT_OTHER_X_TO_ACCOUNT_BALANCE = "204";
}
@
