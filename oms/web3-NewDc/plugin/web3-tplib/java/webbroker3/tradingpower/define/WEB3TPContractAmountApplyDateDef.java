head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.51.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPContractAmountApplyDateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �u�������ʑ���v��J�n���v�̒萔��`�C���^�[�t�F�[�X(WEB3TPContractAmountApplyDateDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/07/15 nakazato(DIR-ST) �V�K�쐬
 */
package webbroker3.tradingpower.define;


/**
 * (�u�������ʑ���v��J�n���v�̒萔��`�C���^�[�t�F�[�X)
 */
public interface WEB3TPContractAmountApplyDateDef
{
    /**
     * (T+1�ȍ~��蔽�f)<BR>
     */
    public final static String DEFAULT = "0";

    /**
     * (�������ȍ~��蔽�f)<BR>
     */
    public final static String FROM_BIZ_DATE = "1";

    /**
     * (T+2�ȍ~��蔽�f)<BR>
     */
    public final static String FROM_T2 = "2";
}
@
