head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.40.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MethodTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3MethodTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/28 li-yingyuan(sinocom)�@@�V�K�쐬
*/
package webbroker3.common.define;

/**
 * �d�@@�敪�@@�萔��`�C���^�t�F�C�X
 *                                                                      
 * @@author li-yingyuan
 * @@version 1.0
 */
public interface WEB3MethodTypeDef
{
    /**
     * 00�F�{���Ȃ��E(�D)�Ȃ��@@
     */
    public static final String NO_ISSUE_TICKET_NO_PREFERENTIAL_SECURITIES = "00";

    /**
     * 01�F�{���Ȃ��E(�D)���� 
     */
    public static final String NO_ISSUE_TICKET_PREFERENTIAL_SECURITIES = "01";
    
    /**
     * 10�F�{������E(�D)�Ȃ��@@
     */
    public static final String ISSUE_TICKET_NO_PREFERENTIAL_SECURITIES = "10";
    
    /**
     * 11�F�{������E(�D)����
     */
    public static final String ISSUE_TICKET_PREFERENTIAL_SECURITIES = "11";

}
@
