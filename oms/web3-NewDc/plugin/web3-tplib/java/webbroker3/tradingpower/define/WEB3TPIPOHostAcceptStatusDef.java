head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.49.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPIPOHostAcceptStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3TPIPOHostAcceptStatusDef.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/24 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpower.define;

/**
 * IPO�\�������@@��t��ԁ@@�萔��`�C���^�t�F�C�X
 * �i�܂�web3-ipo�ɂĖ���`�̂��ߗ]�͎����p�ɒ�`)
 * @@author kazumi HORINO
 *
 */
public interface WEB3TPIPOHostAcceptStatusDef {
    
    /**
     * 0�FDEFAULT�i�����l�j�@@
     */     
    public final static String DEFAULT = "0"; 
    
    /**
     * 1�FSONAR���M�ρ@@
     */
    public final static String ACCEPTED = "1"; 
    
    
    /**
     * 2�FSONAR���f�ς�                                                                       
     */
    public final static String PROCESSED = "2"; 
             
}
@
