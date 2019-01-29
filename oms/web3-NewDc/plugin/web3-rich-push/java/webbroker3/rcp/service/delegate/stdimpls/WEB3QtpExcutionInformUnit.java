head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.32.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpExcutionInformUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QtpExcutionInformUnit�N���X(WEB3QtpExcutionInformUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/10 ��(FLJ) �V�K�쐬
                 : 2009/06/03 ��(FTL) ���Ή�
*/

package webbroker3.rcp.service.delegate.stdimpls;

/**
 * �ꌏ���ʒm�f�[�^�ۑ��@@�I�u�W�F�N�g
 * 
 * @@author ��(FLJ)
 * @@version 1.0
 */
public class WEB3QtpExcutionInformUnit
{
 
    /** �v�f�@@���[�g�E�ʒm�d���E�ʒm�w�b�_�̒ʒm�d���ʔԑ��� */
    private String srlnum;

    /** �v�f�@@���[�g�E�ʒm�d���E�ʒm�w�b�_�̑��M��h�c����*/
    private String sid;

    /** �v�f�@@���[�g�E�ʒm�d���E�^�C���X�^���v���� */
    private String tm;

    /**
     * �v�f�@@���[�g�E�ʒm�d���E�E�����N�摮���̂�CONTENTS=:<br/>
     * view�f�[�^ order_type | URL | �����̕\��<br/>
     * 001�F���������� | CONTENTS=URL4 | ����/���t<br/>
     * 002�F���������� | CONTENTS=URL4 | ����/���p<br/>
     * 003�F�V�K�������� | CONTENTS=URL10 | �M�p/����<br/>
     * 004�F�V�K�������� | CONTENTS=URL10 | �M�p/����<br/>
     * 005�F�����ԍϒ����i���ԍρj | CONTENTS=URL10 | �M�p/�����ԍ�<br/>
     * 006�F�����ԍϒ����i���ԍρj | CONTENTS=URL10 | �M�p/�����ԍ�<br/>
     * 007�F�������� | CONTENTS=URL10 | �M�p/����<br/>
     * 008�F���n���� | CONTENTS=URL10 | �M�p/���n<br/>
     * 601�F�敨�V�K�������� | CONTENTS=URL15 | �敨/����<br/>
     * 602�F�敨�V�K�������� | CONTENTS=URL15 | �敨/����<br/>
     * 603�F�敨�������ԍϒ����i���ԍρj | CONTENTS=URL15 | �敨/�����ԍ�<br/>
     * 604�F�敨�������ԍϒ����i���ԍρj | CONTENTS=URL15 | �敨�����ԍ�<br/>
     * 605�FOP�V�K�������� | CONTENTS=URL20 | OP/����<br/>
     * 606�FOP�V�K�������� | CONTENTS=URL20 | OP/����<br/>
     * 607�FOP�������ԍϒ����i���ԍρj | CONTENTS=URL20 | OP/�����ԍ�<br/>
     * 608�FOP�������ԍϒ����i���ԍρj | CONTENTS=URL20 | OP/�����ԍ�<br/>
     */
    private String url;

    /** �v�f�@@���[�g�E�ʒm�d���E�ʒm���b�Z�[�W�E�ʒm���b�Z�[�W�^�C�g������ */
    private String title;

    /**
     * �v�f�@@���[�g�E�ʒm�d���E�E�����N�摮���̂�,QUOTE=:<br/>
     * �����R�[�h�iQUOTE�j�̕ҏW���@@�F<br/>
     * �،��ŗL�R�[�h / ������E�����ʃR�[�h<br/>
     * �i������E�����ʃR�[�h�ݒ肵�Ȃ��ꍇ�A/ ���ȗ��j
     */
    private String urlParams;

    /**
     * �v�f�@@���[�g�E�ʒm�d���E�ʒm���b�Z�[�W�E���������E�����R�[�h����<br/>
     * �Q��urlParams
     */
    private String qcodeParams;

    /**
     * �v�f�@@���[�g�E�ʒm�d���E�ʒm���b�Z�[�W�E���������̖{��<br/>
     * view�f�[�^.������ + ( + �����R�[�h + / + �s�ꖼ +)
     */
    private String qcodeName;

    /**
     * �v�f�@@���[�g�E�ʒm�d���E�ʒm���b�Z�[�W�E�P���̐���<br/>
     * ��F�@@�y�M�p/�����@@�P���F�z
     */
    private String orderTypeName;

    /** �v�f�@@���[�g�E�ʒm�d���E�ʒm���b�Z�[�W�E�P���̖{�� */
    private String prc;

    /**
     * �v�f�@@���[�g�E�ʒm�d���E�ʒm���b�Z�[�W�E���ʂ̐���<br/>
     * ��F�@@�y���ʁF�z
     */
    private String quantityText;

    /** �v�f�@@���[�g�E�ʒm�d���E�ʒm���b�Z�[�W�E���ʂ̖{�� */
    private String vol;

    /**
     * �v�f�@@���[�g�E�ʒm�d���E�ʒm���b�Z�[�W�E�����N�̖{��<br/>
     * ��F�@@�������Ɖ��ʁ�
     * 
     */
    private String lnkText;

    /** �v�f�@@���[�g�E�ʒm�d���E�ʒm���b�Z�[�W�E�t���[�E�t���[���� */
    private String tlgNtcNmsgFtagFatt;

    /**
     * @@return lnkText ��߂��܂��B
     */
    public String getLnkText()
    {
        return lnkText;
    }
    /**
     * @@param lnkText lnkText ��ݒ�B
     */
    public void setLnkText(String lnkText)
    {
        this.lnkText = lnkText;
    }
    /**
     * @@return vol ��߂��܂��B
     */
    public String getVol()
    {
        return vol;
    }
    /**
     * @@param vol vol ��ݒ�B
     */
    public void setVol(String vol)
    {
        this.vol = vol;
    }
    /**
     * @@return orderTypeName ��߂��܂��B
     */
    public String getOrderTypeName()
    {
        return orderTypeName;
    }
    /**
     * @@param orderTypeName orderTypeName ��ݒ�B
     */
    public void setOrderTypeName(String buySellText)
    {
        this.orderTypeName = buySellText;
    }

    /**
     * @@return prc ��߂��܂��B
     */
    public String getPrc()
    {
        return prc;
    }
    /**
     * @@param prc prc ��ݒ�B
     */
    public void setPrc(String prc)
    {
        this.prc = prc;
    }

    /**
     * @@return qcodeName ��߂��܂��B
     */
    public String getQcodeName()
    {
        return qcodeName;
    }
    /**
     * @@param qcodeName qcodeName ��ݒ�B
     */
    public void setQcodeName(String qcodeName)
    {
        this.qcodeName = qcodeName;
    }

    /**
     * @@return quantityText ��߂��܂��B
     */
    public String getQuantityText()
    {
        return quantityText;
    }
    /**
     * @@param quantityText quantityText ��ݒ�B
     */
    public void setQuantityText(String quantityText)
    {
        this.quantityText = quantityText;
    }
    /**
     * @@return sid ��߂��܂��B
     */
    public String getSid()
    {
        return sid;
    }
    /**
     * @@param sid sid ��ݒ�B
     */
    public void setSid(String sid)
    {
        this.sid = sid;
    }
    /**
     * @@return srlnum ��߂��܂��B
     */
    public String getSrlnum()
    {
        return srlnum;
    }
    /**
     * @@param srlnum srlnum ��ݒ�B
     */
    public void setSrlnum(String srlnum)
    {
        this.srlnum = srlnum;
    }
    /**
     * @@return title ��߂��܂��B
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @@param title title ��ݒ�B
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    /**
     * @@return tm ��߂��܂��B
     */
    public String getTm()
    {
        return tm;
    }
    /**
     * @@param tm tm ��ݒ�B
     */
    public void setTm(String tm)
    {
        this.tm = tm;
    }
    /**
     * @@return url ��߂��܂��B
     */
    public String getUrl()
    {
        return url;
    }
    /**
     * @@param url url ��ݒ�B
     */
    public void setUrl(String url)
    {
        this.url = url;
    }
    /**
     * @@return qcodeQuote ��߂��܂��B
     */
    public String getQcodeParams()
    {
        return qcodeParams;
    }
    /**
     * @@param qcodeQuote qcodeQuote ��ݒ�B
     */
    public void setQcodeParams(String qcodeParams)
    {
        this.qcodeParams = qcodeParams;
    }
    /**
     * @@return urlQuote ��߂��܂��B
     */
    public String getUrlParams()
    {
        return urlParams;
    }
    /**
     * @@param urlQuote urlQuote ��ݒ�B
     */
    public void setUrlParams(String urlParams)
    {
        this.urlParams = urlParams;
    }

    /**
     * �v�f�@@���[�g�E�ʒm�d���E�ʒm���b�Z�[�W�E�t���[�E�t���[�������擾����
     * @@return java.lang.String
     */
    public String getTlgNtcNmsgFtagFatt()
    {
        return tlgNtcNmsgFtagFatt;
    }

    /**
     * �v�f�@@���[�g�E�ʒm�d���E�ʒm���b�Z�[�W�E�t���[�E�t���[������ݒ肷��
     * @@param l_value
     */
    public void setTlgNtcNmsgFtagFatt(String l_value)
    {
        this.tlgNtcNmsgFtagFatt = l_value;
    }

    /**
     * ��ӓ���ł��邽�߂ɂ̎����B
     * 
     * srlnum�@@�Ɓ@@sid�@@�������ł���΁A�����̖��ʒm�I�u�W�F�N�g�Ƃ���
     * 
     */
    public boolean equals(Object l_obj)
    {
        boolean l_result = true;
        if(!(l_obj instanceof WEB3QtpExcutionInformUnit))
        {
            return false;
        }
        WEB3QtpExcutionInformUnit l_tar = (WEB3QtpExcutionInformUnit)l_obj;

        boolean l_res1=false;
        if(srlnum ==null)
        {
            if(l_tar.getSrlnum() == null)
            {
                l_res1 = true;
            }
        }
        else
        {
            if(srlnum.equals(l_tar.getSrlnum()))
            {
                l_res1 = true;
            }
        }

        boolean l_res2 = false;
        if(sid ==null)
        {
            if(l_tar.getSid() == null)
            {
                l_res2 = true;
            }
        }
        else
        {
            if(sid.equals(l_tar.getSid()))
            {
                l_res2 = true;
            }
        }

        return l_res1 && l_res2;
    }

    /**
     * ��ӓ���ł��邽�߂ɂ̎����B
     * 
     * srlnum�@@�Ɓ@@sid�@@�������ł���΁A�����̖��ʒm�I�u�W�F�N�g�Ƃ���
     * 
     */
    public int hashCode()
    {
        StringBuffer l_sb = new StringBuffer();

        if(srlnum!=null)
        {
            l_sb.append(srlnum);
        }
        if(sid!=null)
        {
            l_sb.append(sid);
        }

        return l_sb.toString().hashCode();
    }

    /**
     * ���e�̏o��
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("WEB3QtpExcutionInformUnit, values [");
        l_sb.append("srlnum=").append(srlnum).append(",");
        l_sb.append("sid=").append(sid).append(",");
        l_sb.append("tm=").append(tm).append(",");
        l_sb.append("url=").append(url).append(",");
        l_sb.append("title=").append(title).append(",");
        l_sb.append("urlParams=").append(urlParams).append(",");
        l_sb.append("qcodeParams=").append(qcodeParams).append(",");
        l_sb.append("qcodeName=").append(qcodeName).append(",");
        l_sb.append("orderTypeName=").append(orderTypeName).append(",");
        l_sb.append("prc=").append(prc).append(",");
        l_sb.append("quantityText=").append(quantityText).append(",");
        l_sb.append("vol=").append(vol).append(",");
        l_sb.append("lnkText=").append(lnkText).append(",");
        l_sb.append("tlgNtcNmsgFtagFatt=").append(tlgNtcNmsgFtagFatt);
        l_sb.append("]");

        return l_sb.toString();
    }
}
@
