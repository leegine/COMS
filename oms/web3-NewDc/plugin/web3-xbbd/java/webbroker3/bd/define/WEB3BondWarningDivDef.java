head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondWarningDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �x���敪�C���^�t�F�C�X(WEB3BondWarningDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/25 ����(���u) �V�K�쐬
*/

package webbroker3.bd.define;

/**
 * �x���敪 �萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author ����
 * @@version 1.0 
 */

public interface WEB3BondWarningDivDef
{
    /**
     * 1�F�o�ߗ��q�����m�łȂ��\��
     */
    public static final String ACCRUED_INTEREST_WRONG_POSSIBLE = "1";
    
    /**
     * 2�F��n�������v���Ȃ� �@@�@@
     */
    public static final String ESTIMATED_PRICE_DIFFERENCE = "2";
    
    /**
     * 3�F�]�̓`�F�b�NNG
     */
    public static final String TRADE_POWER_CHECK_NG = "3";
    
    /**
     * 4�F�o�ߗ��q�v�Z�s�\
     */
    public static final String ACCRUED_INTEREST_CANNOT_CALCULATION = "4";
    
    /**
     * 5�F�C�O�s�ꂪ�������ɑ΂��Ĕ�c�Ɠ�
     */
    public static final String FRGN_ORDERBIZDATE_NOBIZDATE = "5";
}
@
