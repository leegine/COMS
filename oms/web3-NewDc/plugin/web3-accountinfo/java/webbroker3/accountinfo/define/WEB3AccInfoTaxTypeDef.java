head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.26.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoTaxTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����敪 �萔��`�C���^�t�F�C�X(WEB3AccInfoTaxTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 ����� (���u) �V�K�쐬
*/
package webbroker3.accountinfo.define;


/**
 * �����敪 �萔��`�C���^�t�F�C�X
 * �������������敪�A�������������敪�i���N�j�A�M�p��������敪�A�M�p��������敪�i���N�j
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3AccInfoTaxTypeDef
{
    
    /**
     * 1�F���J�݁i��ʁj
     */
    public final static String DEFAULT = "1";
    
    /**
     * 2�F�J�ݍς݌��򒥎��Ȃ��i���肩���򒥎��Ȃ��j
     */
    public final static String OPEN_WITHOUT_SPECIAL_WITHHOLD = "2";
    
    /**
     * 3�F�J�ݍς݌��򒥎�����i���肩���򒥎��j
     */
    public final static String OPEN_SPECIAL_WITHHOLD = "3";
}
@
