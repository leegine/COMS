head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioFinancialInstitutionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U������Z�@@�֖���(WEB3AioFinancialInstitutionUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�U������Z�@@�֖���)<BR>
 * �U������Z�@@�֖��׃N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */
public class WEB3AioFinancialInstitutionUnit extends Message
{
    
    /**
     * (�U������Z�@@�փR�[�h)<BR>
     * �U������Z�@@�ւ̃R�[�h
     */
    public String finInstitutionCode;
    
    /**
     * (�U������Z�@@�֖���)<BR>
     * �U������Z�@@�ւ̖���
     */
    public String finInstitutionName;
    
    /**
     * (�U������Z�@@�֖���)<BR>
     * �R���X�g���N�^
     * @@return webbroker3.aio.message.WEB3AioFinancialInstitutionUnit
     * @@roseuid 40E2575103C1
     */
    public WEB3AioFinancialInstitutionUnit() 
    {
     
    }
    
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }       
        if(obj instanceof WEB3AioFinancialInstitutionUnit)
        {
            WEB3AioFinancialInstitutionUnit l_aioFinancialInstitutionUnit = (WEB3AioFinancialInstitutionUnit)obj;
            if(finInstitutionCode.equals(l_aioFinancialInstitutionUnit.finInstitutionCode) 
               && finInstitutionName.equals(l_aioFinancialInstitutionUnit.finInstitutionName))
            {
                return true;
            }
        }
        return false;
    }
}
@
