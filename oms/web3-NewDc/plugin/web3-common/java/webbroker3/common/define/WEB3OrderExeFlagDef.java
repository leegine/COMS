head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.40.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderExeFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʒm���M�t���O�萔��`�C���^�t�F�C�X(WEB3OrderExeFlagDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/28 �h�C(���u) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �ڋq�}�X�^�[�̖��ʒm���M�t���O �萔��`�C���^�t�F�C�X
 *
 * @@author �h�C(���u)
 * @@version 1.0
 */
public interface WEB3OrderExeFlagDef
{
    /**
     * 0�F�����M
     */
    public final static String NOT_SEND_MAIL = "0";

    /**
     * 1�F��{���[���A�h���X
     */
    public final static String BASE_MAIL_ADDRESS = "1";

    /**
     * 2�F���[���A�h���X�Q
     */
    public final static String MAIL_ADDRESS_2 = "2";

    /**
     * 3�F���[���A�h���X�R
     */
    public final static String MAIL_ADDRESS_3 = "3";

    /**
     * 4�F�S�Ẵ��[���A�h���X
     */
    public final static String ALL_MAIL_ADDRESS = "4";
}
@
