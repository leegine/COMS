head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.52.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3VoucherCreateStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �`�[�쐬�󋵒萔��`�C���^�t�F�C�X(WEB3VoucherCreateStatusDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/05/29 �h�C(���u) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �e��A���e�[�u���̓`�[�쐬�� �萔��`�C���^�t�F�C�X
 *
 * @@author �h�C(���u)
 * @@version 1.0
 */
public interface WEB3VoucherCreateStatusDef
{
    /**
     * 0�F���쐬
     */
    public final static String NOT_CREATE  = "0";

    /**
     * 1�F�쐬��
     */
    public final static String CREATE_COMPLETE  = "1";

    /**
     * 2�F��t��
     */
    public final static String INT_ACCEPT  = "2";

    /**
     * 3�F��t����
     */
    public final static String ACCEPT_COMPLETE  = "3";

    /**
     * 4�F��t�G���[
     */
    public final static String ACCEPT_ERROR  = "4";
}
@
