head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.05.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SendRcvDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3SendRcvDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 ���z (���u) �V�K�쐬
*/

package webbroker3.common.define;

/**
 * ����M�敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author ���z
 * @@version 1.0
 */
public interface WEB3SendRcvDivDef
{
    /**
     * 1�F���M��
     */
    public static final String SEND_COMPLETE = "1";
    
    /**
     * 2�F��M��
     */
    public static final String RECEIVE_COMPLETE = "2";
    
    /**
     * 3�F��M�G���[
     */
    public static final String RECEIVE_ERROR = "3";

    /**
     * 0�F�����M
     */
    public static final String NOT_SEND = "0";
}
@
