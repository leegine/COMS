head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.51.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPDoublepositionCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �u��K���`�F�b�N���@@-XXX���v�̒萔��`�C���^�[�t�F�[�X(WEB3TPDoublepositionCheckDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/08/02 nakazato(DIR-ST) �V�K�쐬
 */
package webbroker3.tradingpower.define;


/**
 * (�u��K���`�F�b�N���@@-XXX���v�̒萔��`�C���^�[�t�F�[�X)
 */
public interface WEB3TPDoublepositionCheckDef
{
    /**
     * (�`�F�b�N�Ȃ�)<BR>
     */
    public final static String DEFAULT = "0";

    /**
     * ������ɒ��������̓�K������������K����(�S�̐ݒ�)�𒴂��Ȃ����`�F�b�N����<BR>
     * �����̑����i���t�̏ꍇ�A���̃v���t�@@�����X�l��ݒ肵�Ă��A�S�����`�F�b�N�����B<BR>
     */
    public final static String ORDER_PRODUCT = "1";

    /**
     * ������ɒ��������܂ނ��ׂĂۗ̕L���Y�����ꂩ�̓�K������������K����(�S�̐ݒ�)�𒴂��Ȃ����`�F�b�N����<BR>
     */
    public final static String ALL_PRODUCT = "2";

    /**
     * ������ɒ��������̓�K������������K����(�s��ݒ�)�𒴂��Ȃ����`�F�b�N����<BR>
     * �����̑����i���t�̏ꍇ�A�S�����`�F�b�N�����B<BR>
     * ��(�ی�ˑ�p)�،��U�ւ̏ꍇ�A���������ɂ��āA������K����(�S�̐ݒ�)�Ń`�F�b�N�����B<BR>
     * �������Ƃ��āA���̐ݒ�l�́A�M�p�V�K�����^�������t�^�M�p�����̂ݐݒ肷��B<BR>
     */
    public final static String LISTMARKET_ORDER_PRODUCT = "3";

}
@
