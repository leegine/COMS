head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.15.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	78c4d885a7b5f07;
filename	WEB3QtpRichPushOrderTypeNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3QtpRichPushOrderTypeNameDef�N���X(WEB3QtpRichPushOrderTypeNameDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 ��(FLJ) �V�K�쐬
 */
package webbroker3.rcp.define;

/**
 * �u�����v�̒萔��`�N���X<br>
 *
 * @@author�@@��(FLJ)
 * @@version 1.0
 */
public interface WEB3QtpRichPushOrderTypeNameDef
{
    public static final String EQUITY_BUY = "����/���t";
    public static final String EQUITY_SELL = "����/���p";
    public static final String MARGIN_LONG = "�M�p/����";
    public static final String MARGIN_SHORT = "�M�p/����";
    public static final String CLOSE_MARGIN_LONG = "�M�p/�����ԍ�";
    public static final String CLOSE_MARGIN_SHORT = "�M�p/�����ԍ�";
    public static final String SWAP_MARGIN_LONG = "�M�p/����";
    public static final String SWAP_MARGIN_SHORT = "�M�p/���n";
    public static final String IDX_FUTURES_BUY_TO_OPEN = "�敨/����";
    public static final String IDX_FUTURES_SELL_TO_OPEN = "�敨/����";
    public static final String IDX_FUTURES_BUY_TO_CLOSE = "�敨/�����ԍ�";
    public static final String IDX_FUTURES_SELL_TO_CLOSE = "�敨/�����ԍ�";
    public static final String IDX_OPTIONS_BUY_TO_OPEN = "OP/����";
    public static final String IDX_OPTIONS_SELL_TO_OPEN = "OP/����";
    public static final String IDX_OPTIONS_BUY_TO_CLOSE = "OP/�����ԍ�";
    public static final String IDX_OPTIONS_SELL_TO_CLOSE = "OP/�����ԍ�";

}
@
