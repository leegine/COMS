head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����]�͌���(WEB3TPTradingPowerResult.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 nakazato(ACT) �V�K�쐬
*/
package webbroker3.tradingpower;

import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (����]�͌���)
 */
public class WEB3TPTradingPowerResult
{

    /**
     * (����t���O)<BR>
     * 
     * ����]�̓`�F�b�N���ʂ��Z�b�g�����B<BR>
     * �@@�E����]��OK�̎���true<BR>
     * �@@�E����]��NG�̎���false<BR>
     */
    private boolean resultFlg;

    /**
     * (����\�z)<BR>
     * 
     * ���������E�M�p����̎��A<BR>
     * �������\�z���Z�b�g�����<BR>
     */
    private double tradingPower;

    /**
     * (���ӕ����\���敪)
     * 
     * [1�F�����s�����ӕ����\��]
     * �@@������ʁF�������t�^�������t
     * 
     * �@@(*)�����ڋq���a��،��]�����ڋq�̏ꍇ�ŁA
     * �@@�@@����]�̓`�F�b�N�n�j���������s���ɂȂ�悤�Ȏ����
     * �@@�@@�s�����ꍇ�ɃZ�b�g�B
     * 
     * [2�F���S�ۋK�����ӕ����\��]
     * �@@������ʁF�M�p�V�K�����^�M�p�V�K����
     * 
     * �@@(*)�������������S�ۋK�������̏ꍇ�ɃZ�b�g
     * 
     * [3�F�a����s�����ӕ����\��]
     * �@@������ʁF�������t�^�������t�^�M�p�����^�M�p���n
     * 
     * �@@(*)���Y�ڋq���c�ƓX���X�̏ꍇ�ŁA
     * �@@�@@����]�̓`�F�b�N�m�f�ɂȂ�悤�Ȏ����
     * �@@�@@�s�����ꍇ�ɃZ�b�g
     * 
     * [null�F���ӕ�����\��
     * �@@��L�ȊO�̏ꍇ
     * 
     */
    private String attentionObjectionType;

    /**
     * (�a����s���z)
     * ����]�͌���.���ӕ����\���敪 = 3:�a����s�����ӕ����\���@@�̏ꍇ��
     * ���̗a����s���z���Z�b�g����B
     */
    private double lackAccountBalance;
    
    /**
     * (����]�̓G���[���)<BR>
     * <BR>
     * ������t���O==true�̏ꍇ<BR>
     * �@@����]�̓G���[���I�u�W�F�N�g���Z�b�g�����B<BR>
     * <BR>
     * ������t���O==false�̏ꍇ<BR>
     * �@@null���Z�b�g�����B<BR>
     * <BR>
     */
    private WEB3TPTradingPowerErrorInfo tpErrorInfo;

    /**
     * (�R���X�g���N�^)<BR>
     * @@roseuid 41593C5202CC
     */
    public WEB3TPTradingPowerResult()
    {

    }

    /**
     * (is����t���O)<BR>
     * this.����t���O��ԋp����B<BR>
     * 
     * @@return boolean
     * @@roseuid 41593C1B00D8
     */
    public boolean isResultFlg()
    {
        return this.resultFlg;
    }

    /**
     * (set����t���O)<BR>
     * ����.����t���O��this.����t���O�ɃZ�b�g����B<BR>
     * 
     * @@param l_blnResultFlg - (����t���O)
     * @@roseuid 41593C110230
     */
    public void setResultFlg(boolean l_blnResultFlg)
    {
        this.resultFlg = l_blnResultFlg;
    }

    /**
     * (get����\�z)<BR>
     * this.����\�z��ԋp����B<BR>
     * 
     * @@return double
     * @@roseuid 41593C2A000D
     */
    public double getTradingPower()
    {
        return this.tradingPower;
    }

    /**
     * (set����\�z)<BR>
     * ����.����\�z��this.����\�z�ɃZ�b�g����B<BR>
     * 
     * @@param l_dblTradingPower - (����\�z)
     * @@roseuid 41593C23007A
     */
    public void setTradingPower(double l_dblTradingPower)
    {
        this.tradingPower = l_dblTradingPower;
    }

    /**
     * (get���ӕ����\���敪)<BR>
     * this.���ӕ����\���敪��ԋp����B<BR>
     * 
     * @@return String
     */
    public String getAttentionObjectionType()
    {
        return this.attentionObjectionType;
    }

    /**
     * (set���ӕ����\���敪)<BR>
     * ����.���ӕ����\���敪��this.���ӕ����\���敪�ɃZ�b�g����B<BR>
     * 
     * @@param l_strType - (���ӕ����\���敪)
     */
    public void setAttentionObjectionType(String l_strType)
    {
        this.attentionObjectionType = l_strType;
    }

    /**
     *(get�a����s���z)
     *
     *this.�a����s���z��ԋp����B
     *
     */
    public double getLackAccountBalance()
    {
        return this.lackAccountBalance;
    }

    /**
     * (set�a����s���z)
     * 
     * ����.�a����s���z��this.�a����s���z�ɃZ�b�g����B
     * 
     * @@param l_dblTradingPower - (�a����s���z)
     */
    public void setLackAccountBalance(double l_dblLackAccountBalance)
    {
        this.lackAccountBalance = l_dblLackAccountBalance;
    }

    /**
     * (get����]�̓G���[���
     * 
     * this.����]�̓G���[����ԋp����B 
     * @@return double
     */
    public WEB3TPTradingPowerErrorInfo getTpErrorInfo()
    {
        return this.tpErrorInfo;
    }

    /**
     * (set����]�̓G���[���)
     * 
     * ����.����]�̓G���[����this.����]�̓G���[���ɃZ�b�g����B
     * @@param l_tpErrorInfo - (����]�̓G���[���)
     */
    public void setTpErrorInfo(WEB3TPTradingPowerErrorInfo l_tpErrorInfo)
    {
        this.tpErrorInfo = l_tpErrorInfo;
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        l_builder.append("resultFlg", this.isResultFlg());
        l_builder.append("tradingPower", this.tradingPower);
        l_builder.append("attentionObjectionType", this.attentionObjectionType);
        l_builder.append("lackAccountBalance", this.lackAccountBalance);
        if(this.tpErrorInfo != null)
        {
            l_builder.append("tpErrorInfo", this.tpErrorInfo.toString());
        }
        else
        {
            l_builder.append("tpErrorInfo", this.tpErrorInfo);
        }


        return l_builder.toString();
    }
}
@
