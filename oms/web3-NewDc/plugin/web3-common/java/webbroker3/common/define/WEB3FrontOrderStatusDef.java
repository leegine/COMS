head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.46.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FrontOrderStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �t�����g���������敪�萔��`�C���^�t�F�C�X(WEB3FrontOrderStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/21 �Г�(���u)�@@�V�K�쐬
Revesion History : 2006/5/25 ������ (���u) �C���^�[�t�F�C�X�\���ENo079
*/
package webbroker3.common.define;

/**
 * �t�����g���������敪 �萔��`�C���^�t�F�C�X
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3FrontOrderStatusDef
{
    /**
     * 0�F������
     */
    public static final String NOT_DEAL = "0";

    /**
     * 1�F���M��
     */
    public static final String SENDED = "1";

    /**
     * 2�FAMG���͊���
     */
    public static final String AMG_INPUT_COMPLETE = "2";

    /**
     * 3�F�s����͊���
     */
    public static final String MARKET_INPUT_COMPLETE = "3";

    /**
     * 6�FAMG���̓G���[
     */
    public static final String AMG_INPUT_ERROR = "6";

    /**
     * 7�F�s����̓G���[
     */
    public static final String MARKET_INPUT__ERROR = "7";

    /**
     * 8�F�s���t�m�F��
     */
    public static final String MARKET_ACCEPT_CONFIRMED = "8";

    /**
     * 9�F�s���t�m�F��
     */
    public static final String MARKET_ACCEPT_CONFIRMING = "9";

    /**
     * 4�F�Ǘ��Ҏ蓮������
     */
    public static final String ADMIN_MANUAL_EXPIRED = "4";
}
@
