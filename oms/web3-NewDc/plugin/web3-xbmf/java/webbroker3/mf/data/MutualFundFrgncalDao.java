head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFundFrgncalDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.mf.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link MutualFundFrgncalDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link MutualFundFrgncalRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundFrgncalPK 
 * @@see MutualFundFrgncalRow 
 */
public class MutualFundFrgncalDao extends DataAccessObject {


  /** 
   * ����{@@link MutualFundFrgncalDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private MutualFundFrgncalRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link MutualFundFrgncalRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link MutualFundFrgncalDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link MutualFundFrgncalDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link MutualFundFrgncalRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MutualFundFrgncalRow )
                return new MutualFundFrgncalDao( (MutualFundFrgncalRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MutualFundFrgncalRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MutualFundFrgncalRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link MutualFundFrgncalRow}�I�u�W�F�N�g 
    */
    protected MutualFundFrgncalDao( MutualFundFrgncalRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link MutualFundFrgncalRow}�I�u�W�F�N�g���擾���܂��B
   */
    public MutualFundFrgncalRow getMutualFundFrgncalRow() {
        return row;
    }


  /** 
   * �w���{@@link MutualFundFrgncalRow}�I�u�W�F�N�g����{@@link MutualFundFrgncalDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link MutualFundFrgncalRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link MutualFundFrgncalDao}�擾�̂��߂Ɏw���{@@link MutualFundFrgncalRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link MutualFundFrgncalDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static MutualFundFrgncalDao forRow( MutualFundFrgncalRow row ) throws java.lang.IllegalArgumentException {
        return (MutualFundFrgncalDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MutualFundFrgncalRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link MutualFundFrgncalRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link MutualFundFrgncalPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link MutualFundFrgncalParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MutualFundFrgncalRow.TYPE );
    }


  /** 
   * {@@link MutualFundFrgncalRow}����ӂɓ��肷��{@@link MutualFundFrgncalPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link MutualFundFrgncalRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link MutualFundFrgncalParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link MutualFundFrgncalPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static MutualFundFrgncalPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link MutualFundFrgncalRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_holiday �����Ώۂł���p_holiday�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MutualFundFrgncalRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MutualFundFrgncalRow findRowByPk( String p_institutionCode, String p_productCode, java.sql.Timestamp p_holiday ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundFrgncalPK pk = new MutualFundFrgncalPK( p_institutionCode, p_productCode, p_holiday );
        return findRowByPk( pk );
    }


  /** 
   * �w���MutualFundFrgncalPK�I�u�W�F�N�g����{@@link MutualFundFrgncalRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����MutualFundFrgncalPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MutualFundFrgncalRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MutualFundFrgncalRow findRowByPk( MutualFundFrgncalPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MutualFundFrgncalRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,java.sql.Timestamp)}�����{@@link #forRow(MutualFundFrgncalRow)}���g�p���Ă��������B 
   */
    public static MutualFundFrgncalDao findDaoByPk( String p_institutionCode, String p_productCode, java.sql.Timestamp p_holiday ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundFrgncalPK pk = new MutualFundFrgncalPK( p_institutionCode, p_productCode, p_holiday );
        MutualFundFrgncalRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(MutualFundFrgncalPK)}�����{@@link #forRow(MutualFundFrgncalRow)}���g�p���Ă��������B 
   */
    public static MutualFundFrgncalDao findDaoByPk( MutualFundFrgncalPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MutualFundFrgncalRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_productCode, p_holiday, and �ɂĎw��̒l�����ӂ�{@@link MutualFundFrgncalRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_holiday �����Ώۂł���p_holiday�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_productCode, p_holiday, and �̒l�ƈ�v����{@@link MutualFundFrgncalRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static MutualFundFrgncalRow findRowByInstitutionCodeProductCodeHoliday( String p_institutionCode, String p_productCode, java.sql.Timestamp p_holiday ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MutualFundFrgncalRow.TYPE,
            "institution_code=? and product_code=? and holiday=?",
            null,
            new Object[] { p_institutionCode, p_productCode, p_holiday } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MutualFundFrgncalRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MutualFundFrgncalDao.findRowsByInstitutionCodeProductCodeHoliday(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeProductCodeHoliday(String, String, java.sql.Timestamp)}�����{@@link #forRow(MutualFundFrgncalRow)}���g�p���Ă��������B 
   */
    public static MutualFundFrgncalDao findDaoByInstitutionCodeProductCodeHoliday( String p_institutionCode, String p_productCode, java.sql.Timestamp p_holiday ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductCodeHoliday( p_institutionCode, p_productCode, p_holiday ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_productCode, and �ɂĎw��̒l�Ɉ�v����{@@link MutualFundFrgncalRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_productCode, and �̒l�ƈ�v����{@@link MutualFundFrgncalRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeProductCode( String p_institutionCode, String p_productCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MutualFundFrgncalRow.TYPE,
            "institution_code=? and product_code=?",
            null,
            new Object[] { p_institutionCode, p_productCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeProductCode(String, String)}�����{@@link #forRow(MutualFundFrgncalRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeProductCode( String p_institutionCode, String p_productCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeProductCode( p_institutionCode, p_productCode ) );
    }

}
@
