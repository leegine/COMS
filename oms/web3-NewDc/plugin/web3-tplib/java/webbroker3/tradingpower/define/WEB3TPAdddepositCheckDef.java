head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.49.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAdddepositCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �u�Ǐؗ]�̓`�F�b�N���@@-XXX���v�̒萔��`�C���^�[�t�F�[�X(WEB3TPAdddepositCheckDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/07/27 nakazato(DIR-ST) �V�K�쐬
 */
package webbroker3.tradingpower.define;


/**
 * (�u�Ǐؗ]�̓`�F�b�N���@@-XXX���v�̒萔��`�C���^�[�t�F�[�X)
 */
public interface WEB3TPAdddepositCheckDef
{
    /**
     * (�`�F�b�N�Ȃ�)<BR>
     */
    public final static String DEFAULT = "0";

    /**
     * (T+�������`T+��n��-1�i��n���O���j�̒Ǐؗ]�͂�0�����łȂ����`�F�b�N����)<BR>
     */
    public final static String FROM_BIZ_DATE_UNTIL_PRE_DELIVERY_DATE = "1";

    /**
     * (T+�������i�������j�̒Ǐؗ]�͂�0�����łȂ����`�F�b�N����)<BR>
     */
    public final static String ON_BIZ_DATE = "2";
}
@
