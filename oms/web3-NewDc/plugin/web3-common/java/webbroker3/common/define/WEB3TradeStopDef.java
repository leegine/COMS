head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.35.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TradeStopDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �c�Ɠ��敪FLAG�萔��`�N���X(WEB3TradeStopDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/12 ����@@���j(SRA) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * ������~�t���O��`�N���X
 *
 * @@author ����@@���j(SRA)
 * @@version 1.0
 */
public interface WEB3TradeStopDef
{

    /**
     * 0�FDEFAULT
     */
    public final static String ACTIVE = "0";

    /**
     * 1�F��~���i������K��
     */
    public final static String STOP_MARKET_DEREG = "1";

    /**
     * 2�F��~���i���ЋK���j
     */
    public final static String STOP_COMPANY_DEREG = "2";
}
@
