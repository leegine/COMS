head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.25.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoSearchCondTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������敪(WEB3AccInfoSearchCondTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/9/22 ������(sinocom) �V�K�쐬
*/
package webbroker3.accountinfo.define;

/**
 * ���������敪 �萔��`�C���^�t�F�C�X<BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public interface WEB3AccInfoSearchCondTypeDef 
{
    /**
     * 0�F�@@0�F�S�āiALL�j�@@�@@
     */
    public final static String ALL = "0";
    
    /**
     * 1�F�@@1�FY�q�iYACCOUNT�j�@@�@@
     */
    public final static String YACCOUNT = "1";
    
    /**
     * 2�F�@@2�F�Ǘ����b�N�iADMINLOCK�j�@@�@@
     */
    public final static String ADMINLOCK = "2";
    
    /**
     * 3�F�@@3�F�x�X���b�N�iBRANCHLOCK�j�@@�@@
     */
    public final static String BRANCHLOCK = "3";
}
@
