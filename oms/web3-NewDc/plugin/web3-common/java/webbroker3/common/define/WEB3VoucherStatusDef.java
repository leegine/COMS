head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.26.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3VoucherStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �`�[�쐬�X�e�[�^�X(WEB3VoucherStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 ����� (���u) �V�K�쐬
*/

package webbroker3.common.define;

/**
 * �`�[�쐬�X�e�[�^�X �萔��`�C���^�t�F�C�X
 *
 * @@author �����
 * @@version 1.0
 */
public interface WEB3VoucherStatusDef
{

    /**
     * 0�FDEFAULT�i�`�[���쐬�j
     */
    public final static String DEFAULT  = "0";

    /**
     * 1�F�쐬��
     */
    public final static String CREATE_COMPLETE  = "1";

    /**
     * 2�F���M�ۗ���
     */
    public final static String SEND_RESERVING  = "2";

    /**
     * 3�F���M��
     */
    public final static String SEND_COMPLETE  = "3";

    /**
     * 4�F��M��
     */
    public final static String RECEIVE_COMPLETE  = "4";

    /**
     * 5�F���M�G���[
     */
    public final static String SEND_ERROR  = "5";

    /**
     * 6�F��M�G���[
     */
    public final static String RECEIVE_ERROR  = "6";

}@
