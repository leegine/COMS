head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.52.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPShortfallGenerationStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �s���������󋵂̒萔��`�C���^�[�t�F�[�X(WEB3TPShortfallGenerationStateDivDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/10/20 ������ �V�K�쐬
 */
package webbroker3.tradingpower.define;

/**
 * (�s���������󋵂̒萔��`�C���^�[�t�F�[�X)
 */
public interface WEB3TPShortfallGenerationStateDivDef
{
    /**
     *  0 : �s����������<BR>
     */
    public final static String SHORTFALL_NOT_GENERATION = "0";

    /**
     *  1 : �s��������<�����ڋq><BR>
     */
    public final static String SHORTFALL_GENERATION_EQUITY_ACCOUNT = "1";

    /**
     *  2 : �s��������<�M�p�ڋq><BR>
     */
    public final static String SHORTFALL_GENERATION_MARGIN_ACCOUNT = "2";
}
@
