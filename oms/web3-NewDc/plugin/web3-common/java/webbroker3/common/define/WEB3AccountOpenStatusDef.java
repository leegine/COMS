head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.28.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccountOpenStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �J�ݏ󋵒萔��`�C���^�t�F�C�X(WEB3AccountOpenStatusDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/06 �ЋŃV��(���u) �V�K�쐬
*/
package webbroker3.common.define;
/**
 * �J�ݏ� �萔��`�C���^�t�F�C�X
 *
 * @@author �ЋŃV��(���u)
 * @@version 1.0
 */
public interface WEB3AccountOpenStatusDef
{
    /**
     * 0�F�\��
     */
    public final static String REQUEST= "0";

    /**
     * 1�F�J��
     */
    public final static String OPEN = "1";
    
    /**
     * 2�F����
     */
    public final static String PROMISE = "2";
    
    /**
     * 3�F���
     */
    public final static String RELEASE = "3";
    
    /**
     * 4�F��
     */
    public final static String CLOSEDOWN = "4";
}
@
