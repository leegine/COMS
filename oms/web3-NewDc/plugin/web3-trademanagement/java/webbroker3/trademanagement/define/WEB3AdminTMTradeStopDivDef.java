head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.24.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMTradeStopDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Trademanagement Trade Stop Div Def(WEB3AdminTMTradeStopDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/05 ����� (���u) �V�K�쐬
*/
package webbroker3.trademanagement.define;

/**
 * Trademanagement Trade Stop Div Def<BR>
 * WEB3AdminTMTradeStopDivDef interface<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public interface WEB3AdminTMTradeStopDivDef
{
    /**
     *  0�F����\
     */
    public final static String NORMAL  = "0";

    /**
     * 1�F�����~
     */
    public final static String SUSPENTION = "1";

    /**
     * 2�F�@@�戵�s��
     */
    public final static String DISABLE = "2";
}@
