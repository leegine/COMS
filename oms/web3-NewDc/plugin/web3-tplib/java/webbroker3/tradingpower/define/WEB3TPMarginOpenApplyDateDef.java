head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.49.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMarginOpenApplyDateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �u�M�p�V�K���\�z�K�p���v�̒萔��`�C���^�[�t�F�[�X(WEB3TPMarginOpenApplyDateDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/07/22 nakazato(DIR-ST) �V�K�쐬
 */
package webbroker3.tradingpower.define;

/**
 * (�u�M�p�V�K���\�z�K�p���v�̒萔��`�C���^�[�t�F�[�X)
 */
public interface WEB3TPMarginOpenApplyDateDef
{

    /**
     * (T+1�ȍ~�ōŏ��̐M�p�V�K���\�z���̗p����B) <BR>
     */
    public final static String DEFAULT = "0";

    /**
     * (�������ȍ~�ōŏ��̐M�p�V�K���\�z���̗p����B) <BR>
     */
    public final static String FROM_BIZ_DATE_UNTIL_T5 = "1";

    /**
     * (T+2�ȍ~�ōŏ��̐M�p�V�K���\�z���̗p����B) <BR>
     */
    public final static String FROM_T2_UNTIL_T5 = "2";
}@
