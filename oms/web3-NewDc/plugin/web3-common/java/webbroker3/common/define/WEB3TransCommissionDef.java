head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.02.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TransCommissionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֎萔���敪(WEB3TransCommissionDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 ����� (���u) �V�K�쐬
*/

package webbroker3.common.define;

/**
 * �U�֎萔���敪 �萔��`�C���^�t�F�C�X
 *
 * @@author �����
 * @@version 1.0
 */
public interface WEB3TransCommissionDef
{

    /**
     * 1�F���s���X����
     */
    public final static String SAME_TRADE_STORE_FREE  = "1";

    /**
     * 2�F���s
     */
    public final static String SAME_TRADE  = "2";

    /**
     * 4�F���s
     */
    public final static String OTHER_TRADE  = "4";

    /**
     * 5�F���s���X�L��
     */
    public final static String SAME_TRADE_STORE_CHARGE  = "5";

    /**
     * 9�F���̑�
     */
    public final static String OTHER  = "9";

}@
