head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.36.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O��������������(WEB3FeqProductUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import java.util.Date;

/**
 * (�O��������������)<BR>
 * �O�������������׃N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqProductUnit extends WEB3FeqProductCodeNameUnit 
{
    
    /**
     * (���t�\)<BR>
     * ���t�\<BR>
     * <BR>
     * true�F�@@�\<BR>
     * false�F�@@�s��<BR>
     */
    public boolean buyPossFlag;
    
    /**
     * (���t�\)<BR>
     * ���t�\<BR>
     * <BR>
     * true�F�@@�\<BR>
     * false�F�@@�s��<BR>
     */
    public boolean sellPossFlag;
    
    /**
     * (���n�����R�[�h)<BR>
     * ���n�����R�[�h<BR>
     */
    public String localProductCode;
    
    /**
     * (���t�P��)<BR>
     * ���t�P��<BR>
     */
    public String buyUnit;
    
    /**
     * (�Œᔃ�t�P��)<BR>
     * �Œᔃ�t�P��<BR>
     */
    public String minBuyUnit;
    
    /**
     * (���t�P��)<BR>
     * ���t�P��<BR>
     */
    public String sellUnit;
    
    /**
     * (�Œᔄ�t�P��)<BR>
     * �Œᔄ�t�P��<BR>
     */
    public String minSellUnit;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;
    
    /**
     * (���o�^��)<BR>
     * ���o�^��<BR>
     */
    public Date listedDate;
    
    /**
     * (���p�~��)<BR>
     * ���p�~��<BR>
     */
    public Date unlistedDate;
    
    /**
     * (�O��������������)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 4200C778027B
     */
    public WEB3FeqProductUnit() 
    {
     
    }
}
@
