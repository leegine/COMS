head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.27.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExpirationReasonCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3ExpirationReasonCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)�@@�V�K�쐬
Revesion History : 2006/06/14 ������(���u) �C���^�[�t�F���X�\���ENo083
*/
package webbroker3.common.define;

/**
 * �������R�R�[�h�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3ExpirationReasonCodeDef
{
    /**
     * 1 : :�����������ʂ����ׂĖ��s�����̏ꍇ
     */
    public static final String FAILED_EXECUTE = "1";

    /**
     * 2 : �����������ʂ̈ꕔ����萬�����c���ʂ����������ꍇ
     */
    public static final String PARTIALLY_EXECUTE_CLOSE = "2";

    /**
     * 3 : �������s�����t�������U���o�������������ꍇ
     */
    public static final String LAST_QUOTATION_CLOSE = "3";

    /**
     * 4 : �����l���I�[�o�[
     */
    public static final String PRICE_RANGE_EXCESS = "4";

    /**
     * 8 : ��X�ɂ�鎸��
     */
    public static final String EXPIRED_BY_AGENT = "8";

    /**
     * 9 : �������j��
     */
    public static final String ORDER_CANCEL = "9";
}
@
