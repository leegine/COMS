head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.07.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DuplicationMailAddressCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[���d���`�F�b�N�萔��`�C���^�t�F�C�X(WEB3DuplicationMailAddressCheckDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/19 ������(���u) �V�K�쐬
*/

package webbroker3.common.define;

/**
 * ���[���d���`�F�b�N �萔��`�C���^�t�F�C�X
 *
 * @@author ������
 * @@version 1.0
 */
public interface WEB3DuplicationMailAddressCheckDef
{

	/**
     * 0�F�d�����[���A�h���X�`�F�b�N���s��Ȃ��B<BR>
     */
    public final static String DEFAULT = "0";

    /**
     * 1�F�d�����[���A�h���X�`�F�b�N���s�Ȃ��B<BR>
     * �i�d���A�h���X���ݎ��A��O���������j<BR>
     */
    public final static String NO_EXCEPTION = "1";

    /**
     * 2�F�d�����[���A�h���X�`�F�b�N���s�Ȃ��B<BR>
     * �i�d���A�h���X���ݎ��A��O�𐶐��j<BR>
     */
    public final static String CREATE_EXCEPTION = "2";

    /**
     * 3�F�d�����[���A�h���X�`�F�b�N���s�Ȃ��B<BR>
     * �i�d���A�h���X���ݎ��A�u���O�C�����[�U���Ǘ��҈ȊO�̏ꍇ�v�͗�O�𐶐��j<BR>
     */
    public final static String CREATE_EXCEPTION_CUST = "3";

    /**
     * 4�F�d�����[���A�h���X�`�F�b�N���s�Ȃ��B<BR>
     * �i�d���A�h���X���ݎ��A�u���O�C�����[�U���Ǘ��҂̏ꍇ�v�͗�O�𐶐��j<BR>
     */
    public final static String CREATE_EXCEPTION_ADMIN = "4";
} @
