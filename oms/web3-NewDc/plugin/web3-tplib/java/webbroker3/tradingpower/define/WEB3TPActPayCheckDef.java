head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.50.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPActPayCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �u���o�\�����`�F�b�N���@@-XXX���v�̒萔��`�C���^�[�t�F�[�X(WEB3TPActPayCheckDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/07/27 nakazato(DIR-ST) �V�K�쐬
 */
package webbroker3.tradingpower.define;


/**
 * (�u���o�\�����`�F�b�N���@@-XXX���v�̒萔��`�C���^�[�t�F�[�X)
 */
public interface WEB3TPActPayCheckDef
{
    /**
     * (�`�F�b�N�Ȃ�)<BR>
     */
    public final static String DEFAULT = "0";

    /**
     * (T+0�`T+�������i�������j�̈��o�\������0�����łȂ����`�F�b�N����)<BR>
     */
    public final static String FROM_T0_UNTIL_BIZ_DATE = "1";

    /**
     * (T+0�`T+������-1�i�������O���j�̈��o�\������0�����łȂ����`�F�b�N����)<BR>
     */
    public final static String FROM_T0_UNTIL_PRE_BIZ_DATE = "2";

}
@
