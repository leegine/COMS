head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcConditionPerIndex.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �w���ʏ؋����v�Z�����N���X(WEB3IfoDepositCalcConditionPerIndex.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/10/21 �R�c�@@��i(FLJ) �V�K�쐬
 Revesion History : 2007/06/27 hijikata(SRA) �[��Ή� ���f��No.056�@@
 
 */
package webbroker3.ifodeposit;

/**
 * (�w���ʏ؋����v�Z����)<BR>
 * ��ЁE���X�E�w���ʏ؋����v�Z������\���N���X�B<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoDepositCalcConditionPerIndex
{

    /**
     * �����Y�����R�[�h�B
     */
    private String underlyingProductCode;

    /**
     * �K��؋����B
     */
    private double ifoDepositPerUnit;

    /**
     * �K��؋������b�h�B
     */
    private double ifoDepositPerUnitRed;

    /**
     * �K��؋������؋����s�����m�聄�B
     */
    private double ifoDepositPerUnitTemp;

    /**
     * (�w���ʏ؋����v�Z����)<BR>
     * 
     * �R���X�g���N�^�B<BR>
     * @@roseuid 4112E91000DB
     */
    public WEB3IfoDepositCalcConditionPerIndex()
    {

    }

    /**
     * (set�����Y�����R�[�h)<BR>
     * 
     * ����.�����Y�����R�[�h��this.�����Y�����R�[�h�ɃZ�b�g����B<BR>
     * @@param l_strUnderlyingProductCode - �����Y�����R�[�h�B
     * @@roseuid 411327A60095
     */
    public void setUnderlyingProductCode(String l_strUnderlyingProductCode)
    {
        underlyingProductCode = l_strUnderlyingProductCode;
    }

    /**
     * (set�K��؋���)<BR>
     * 
     * ����.�K��؋�����this.�K��؋����ɃZ�b�g����B<BR>
     * @@param l_dblIfoDepositPerUnit - �K��؋���
     * @@roseuid 4113271002E7
     */
    public void setIfoDepositPerUnit(double l_dblIfoDepositPerUnit)
    {
        ifoDepositPerUnit = l_dblIfoDepositPerUnit;
    }

    /**
     * (set�K��؋������b�h)<BR>
     * 
     * ����.�K��؋������b�h��this.�K��؋������b�h�ɃZ�b�g����B<BR>
     * @@param l_dblIfoDepositPerUnitRed - �K��؋������b�h
     * @@roseuid 41132769024A
     */
    public void setIfoDepositPerUnitRed(double l_dblIfoDepositPerUnitRed)
    {
        ifoDepositPerUnitRed = l_dblIfoDepositPerUnitRed;
    }

    /**
     * (set�K��؋������؋����s�����m�聄)<BR>
     * 
     * ����.�K��؋������؋����s�����m�聄��this.�K��؋������؋����s�����m�聄�ɃZ�b�g����B<BR>
     * @@param l_dblIfoDepositPerUnitTemp - �K��؋������؋����s�����m�聄
     */
    public void setIfoDepositPerUnitTemp(double l_dblIfoDepositPerUnitTemp)
    {
        ifoDepositPerUnitTemp = l_dblIfoDepositPerUnitTemp;
    }

    /**
     * (get�����Y�����R�[�h)<BR>
     * 
     * this.�����Y�����R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 4113285E0037
     */
    public String getUnderlyingProductCode()
    {
        return underlyingProductCode;
    }

    /**
     * (get�K��؋���)<BR>
     * 
     * this.�K��؋�����ԋp����B<BR>
     * @@return double
     * @@roseuid 4113289C01FC
     */
    public double getIfoDepositPerUnit()
    {
        return ifoDepositPerUnit;
    }

    /**
     * (get�K��؋������b�h)<BR>
     * 
     * this.�K��؋������b�h��ԋp����B<BR>
     * @@return double
     * @@roseuid 41132B0101ED
     */
    public double getIfoDepositPerUnitRed()
    {
        return ifoDepositPerUnitRed;
    }

	/**
	 * (get�K��؋������؋����s�����m�聄)<BR>
	 * 
	 * this.�K��؋������؋����s�����m�聄��ԋp����B<BR>
	 * @@return double
	 */
	public double getIfoDepositPerUnitTemp()
	{
		return ifoDepositPerUnitTemp;
	}
    
    /**
     * WEB3IfoDepositCalcConditionPerIndex�̕�����\����Ԃ��B 
     * 
     * @@see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("WEB3IfoDepositCalcConditionPerIndex={");
        l_sb.append("underlyingProductCode=").append(getUnderlyingProductCode());
        l_sb.append(",ifoDepositPerUnit=").append(getIfoDepositPerUnit());
        l_sb.append(",ifoDepositPerUnitRed=").append(getIfoDepositPerUnitRed());
        l_sb.append(",ifoDepositPerUnitTemp=").append(getIfoDepositPerUnitTemp());
        l_sb.append("}");
        return l_sb.toString();
    }

}
@
