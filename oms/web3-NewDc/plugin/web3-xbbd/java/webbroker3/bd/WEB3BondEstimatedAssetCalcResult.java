head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondEstimatedAssetCalcResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���T�Z�]���z�v�Z����(WEB3BondEstimatedAssetCalcResult.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17  ꎉ� (���u) �V�K�쐬
 */

package webbroker3.bd;

import java.math.BigDecimal;

/**
 * (���T�Z�]���z�v�Z����)<BR>
 * ���T�Z�]���z�v�Z����<BR>
 * 
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3BondEstimatedAssetCalcResult 
{
    /**
     * (�]���P��)<BR>
     * �]���P��<BR>
     */
    private BigDecimal estimatedPrice;
    
    /**
     * (�T�Z�]���z�i�~�݁j)<BR>
     * �T�Z�]���z�i�~�݁j<BR>
     */
    private BigDecimal estimatedAsset;
    
    /**
     * (�T�Z�]���z�i�O�݁j)<BR>
     * �T�Z�]���z�i�O�݁j<BR>
     */
    private BigDecimal foreignEstimatedAsset;
    
    /**
     * (���T�Z�]���z�v�Z����)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44C085DA025A
     */
    public WEB3BondEstimatedAssetCalcResult() 
    {
     
    }
    
    /**
     * (get�T�Z�]���z�i�~�݁j)<BR>
     * �T�Z�]���z�i�~�݁j���擾����B<BR>
     * @@return BigDecima��
     * @@roseuid 44C088020131
     */
    public BigDecimal getEstimatedAsset() 
    {
         return estimatedAsset;
    }
    
    /**
     * (get�T�Z�]���z�i�O�݁j)<BR>
     * �T�Z�]���z�i�O�݁j���擾����B<BR>
     * @@return BigDecima��
     * @@roseuid 44C088230316
     */
    public BigDecimal getForeignEstimatedAsset() 
    {
         return foreignEstimatedAsset;
    }
    
    /**
     * (get�]���P��)<BR>
     * �]���P�����擾����B<BR>
     * @@return BigDecimal
     * @@roseuid 44C95EDC03BF
     */
    public BigDecimal getEstimatedPrice() 
    {
         return estimatedPrice;
    }
    
    /**
     * (set�T�Z�]���z�i�~�݁j)<BR>
     * �T�Z�]���z�i�~�݁j���Z�b�g����B<BR>
     * @@param l_bdEstimatedAsset - (�T�Z�]���z�i�~�݁j)<BR>
     * �T�Z�]���z�i�~�݁j<BR>
     * @@roseuid 44C95F0E00C2
     */
    public void setEstimatedAsset(BigDecimal l_bdEstimatedAsset) 
    {
         this.estimatedAsset = l_bdEstimatedAsset;
    }
    
    /**
     * (set�T�Z�]���z�i�O�݁j)<BR>
     * �T�Z�]���z�i�O�݁j���Z�b�g����B<BR>
     * @@param l_bdForeignEstimatedAsset - (�T�Z�]���z�i�O�݁j)<BR>
     * �T�Z�]���z�i�O�݁j<BR>
     * @@roseuid 44C95F490083
     */
    public void setForeignEstimatedAsset(BigDecimal l_bdForeignEstimatedAsset) 
    {
        this.foreignEstimatedAsset = l_bdForeignEstimatedAsset;
    }
    
    /**
     * (set�]���P��)<BR>
     * �]���P�����Z�b�g����B<BR>
     * @@param l_bdEstimatedPrice - (�]���P��)<BR>
     * �]���P��<BR>
     * @@roseuid 44C086F601BE
     */
    public void setEstimatedPrice(BigDecimal l_bdEstimatedPrice) 
    {
        this.estimatedPrice = l_bdEstimatedPrice;
    }
}
@
